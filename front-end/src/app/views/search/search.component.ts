import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { FormBuilder, FormGroup } from "@angular/forms";
import { Medication } from "src/app/domain/model/medication.model";
import { MedicationService } from "src/app/services/medication.service";
import { FavoritoService } from "src/app/services/favorito.service";
import { EstoqueService } from "src/app/services/estoque.service";
import { getCurrentUserStoraged } from "src/app/helpers/localstorage";

@Component({
  selector: "app-consulta",
  templateUrl: "./search.component.html",
  styleUrls: ["./search.component.css"],
})
export class ConsultaComponent implements OnInit {
  consultaForm: FormGroup;

  medicamentos: (Medication & { showDetalhes?: boolean })[] = [];
  medicamentosFiltrados: (Medication & { showDetalhes?: boolean })[] = [];

  isLoading = false;
  termoBusca = "";
  buscaRealizada = false;
  favoritos: Set<number> = new Set();

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private medicationService: MedicationService,
    private favoritoService: FavoritoService,
    private estoqueService: EstoqueService
  ) {
    this.consultaForm = this.formBuilder.group({
      termoBusca: [""],
    });
  }

  ngOnInit(): void {
    this.getCurrentUser();
    this.carregarTodosMedicamentos();
    this.carregarFavoritosUsuario();
  }

  getCurrentUser(): void {
    const user = getCurrentUserStoraged();
    if (!user) {
      this.router.navigate(["/login"]);
    }
  }

  carregarTodosMedicamentos(): void {
    this.isLoading = true;

    this.medicationService.getAllMedications().subscribe({
      next: (medicamentos) => {
        this.medicamentos = medicamentos.map((m) => ({
          ...m,
          quantidadeEstoque: 0,
          disponivel: false,
          showDetalhes: false,
          data_cadastro: m.data_cadastro
            ? new Date(m.data_cadastro).toLocaleDateString("pt-BR")
            : "-",
        }));

        this.estoqueService.getAllEstoques().subscribe({
          next: (estoques) => {
            this.medicamentos.forEach((med) => {
              const estoqueMed = estoques.find(
                (e) => e.medicamentoId === med.id
              );

              if (estoqueMed) {
                med.quantidadeEstoque = estoqueMed.quantidade;
                med.disponivel = estoqueMed.quantidade > 0;

                med.farmaciaNome = med.farmaciaNome || "-";

                if (estoqueMed.farmacia?.nomeFantasia) {
                  med.farmaciaNome = estoqueMed.farmacia.nomeFantasia;
                }
              } else {
                med.quantidadeEstoque = 0;
                med.disponivel = false;
                med.farmaciaNome = med.farmaciaNome || "-";
              }
            });

            this.medicamentosFiltrados = this.medicamentos;
            this.isLoading = false;
          },
          error: (err) => {
            console.error("Erro ao carregar estoques:", err);
            this.isLoading = false;
          },
        });
      },
      error: (error) => {
        console.error("Erro ao carregar medicamentos:", error);
        this.isLoading = false;
        this.medicamentos = [];
        this.medicamentosFiltrados = [];
      },
    });
  }

  carregarFavoritosUsuario(): void {
    const user = getCurrentUserStoraged();
    if (!user) return;

    this.favoritoService.getFavoritosByUsuario(user.id).subscribe({
      next: (favoritos) => {
        favoritos.forEach((f) => this.favoritos.add(f.idMedicamento));
      },
      error: (err) =>
        console.error("Erro ao carregar favoritos do usuário:", err),
    });
  }

  onBuscar(): void {
    this.termoBusca = this.consultaForm.get("termoBusca")?.value || "";
    this.isLoading = true;
    this.buscaRealizada = true;

    setTimeout(() => {
      this.medicamentosFiltrados = this.medicamentos
        .filter(
          (m) =>
            m.nome.toLowerCase().includes(this.termoBusca.toLowerCase()) ||
            m.principioAtivo
              .toLowerCase()
              .includes(this.termoBusca.toLowerCase())
        )
        .map((m) => ({ ...m, showDetalhes: false }));

      this.isLoading = false;
    }, 300);
  }

  onLimpar(): void {
    this.consultaForm.reset();
    this.termoBusca = "";
    this.buscaRealizada = false;

    this.medicamentosFiltrados = this.medicamentos.map((m) => ({
      ...m,
      showDetalhes: false,
    }));
  }

  getStatusClass(disponivel: boolean): string {
    return disponivel ? "status-disponivel" : "status-indisponivel";
  }

  getStatusText(disponivel: boolean): string {
    return disponivel ? "Disponível" : "Indisponível";
  }

  verificarEstoque(medicamento: Medication): void {
    this.estoqueService.getQuantidadePorMedicamento(medicamento.id).subscribe({
      next: (qtd) => {
        medicamento.quantidadeEstoque = qtd;
        medicamento.disponivel = qtd > 0;
      },
      error: (err) => console.error("Erro ao buscar estoque:", err),
    });
  }

  toggleFavorito(medicamento: any): void {
    const user = getCurrentUserStoraged();
    if (!user) return;

    if (this.favoritos.has(medicamento.id)) {
      this.favoritos.delete(medicamento.id);
      medicamento.favoritado = false;

      this.favoritoService
        .deleteFavoritoByUserAndMedicamento(user.id, medicamento.id)
        .subscribe({
          next: () => console.log("Removido dos favoritos:", medicamento.nome),
          error: (err) => console.error("Erro ao remover favorito:", err),
        });
    } else {
      this.favoritos.add(medicamento.id);
      medicamento.favoritado = true;

      this.favoritoService.addFavorito(user.id, medicamento.id).subscribe({
        next: () => console.log("Adicionado aos favoritos:", medicamento.nome),
        error: (err) => console.error("Erro ao adicionar favorito:", err),
      });
    }
  }

  isFavorito(medicamentoId: number): boolean {
    return this.favoritos.has(medicamentoId);
  }
}
