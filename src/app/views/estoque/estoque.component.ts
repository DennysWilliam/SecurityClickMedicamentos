import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { EstoqueService } from "src/app/services/estoque.service";
import { MedicationService } from "src/app/services/medication.service";
import { PharmacyService } from "src/app/services/pharmacy.service";
import { Estoque } from "src/app/models/estoque.model";
import { Medication } from "src/app/models/medication.model";
import { Pharmacy } from "src/app/models/pharmacy.model";
import { Router } from "@angular/router";
import { first } from "rxjs";

@Component({
  selector: "app-estoque",
  templateUrl: "./estoque.component.html",
  styleUrls: ["./estoque.component.css"],
})
export class EstoqueComponent implements OnInit {
  isAdmin: boolean = false;

  estoquesExibidos: Estoque[] = [];
  todosEstoques: Estoque[] = [];

  medicamentos: Medication[] = [];
  farmacias: Pharmacy[] = [];
  medicamentosFiltrados: Medication[] = [];

  farmaciaFiltroId: number | null = null;

  estoqueForm!: FormGroup;
  modoEdicao: boolean = false;
  estoqueSelecionado!: Estoque | null;

  constructor(
    private fb: FormBuilder,
    private estoqueService: EstoqueService,
    private medicationService: MedicationService,
    private pharmacyService: PharmacyService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const storedRole = localStorage.getItem("role");
    this.isAdmin = storedRole === "ADMIN";

    this.carregarDados();

    this.estoqueForm = this.fb.group({
      id: [null],
      medicamentoId: [null, Validators.required],
      farmaciaId: [null, Validators.required],
      quantidade: [0, [Validators.required, Validators.min(0)]],
    });
  }

  carregarDados(): void {
    this.estoqueService.getAllEstoques().subscribe((data) => {
      this.todosEstoques = data;
      this.estoquesExibidos = data;
    });

    this.medicationService
      .getAllMedications()
      .subscribe((data) => (this.medicamentos = data));

    this.pharmacyService
      .getAllPharmacies()
      .subscribe((data) => (this.farmacias = data));
  }

  filtrarEstoquePorFarmacia(): void {
    if (this.farmaciaFiltroId === null || this.farmaciaFiltroId === 0) {
      this.estoquesExibidos = this.todosEstoques;
      return;
    }

    this.estoqueService
      .getEstoqueByFarmaciaId(this.farmaciaFiltroId)
      .pipe(first())
      .subscribe({
        next: (data) => (this.estoquesExibidos = data),
        error: () => (this.estoquesExibidos = []),
      });
  }

  aplicarFiltro(value: number): void {
    this.farmaciaFiltroId = value;
    this.filtrarEstoquePorFarmacia();
  }

  selecionarFarmaciaParaForm(event: Event): void {
    const input = event.target as HTMLInputElement;
    const farmaciaNome = input.value;

    const farmacia = this.farmacias.find(
      (f) => f.nomeFantasia === farmaciaNome
    );

    this.estoqueForm.patchValue({
      farmaciaId: farmacia ? farmacia.id : null,
    });

    this.medicamentosFiltrados = this.medicamentos;
  }

  selecionarFarmacia(event: Event): void {
    this.selecionarFarmaciaParaForm(event);
  }

  salvarEstoque(): void {
    if (this.estoqueForm.invalid) return;

    const estoque: Estoque = this.estoqueForm.value;

    if (this.modoEdicao) {
      this.estoqueService.updateEstoque(estoque).subscribe(() => {
        alert("Estoque atualizado com sucesso!");
        this.carregarDados();
        this.cancelarEdicao();
      });
    } else {
      this.estoqueService.createEstoque(estoque).subscribe(() => {
        alert("Estoque criado!");
        this.carregarDados();
        this.estoqueForm.reset({ quantidade: 0 });
      });
    }
  }

  adicionarEstoque(): void {
    this.router.navigate(["/create-medication"]);
  }

  editarEstoque(e: Estoque): void {
    if (!this.isAdmin) return;
    this.estoqueSelecionado = e;

    this.estoqueForm.patchValue({
      id: e.id,
      medicamentoId: e.medicamentoId,
      farmaciaId: e.farmaciaId,
      quantidade: e.quantidade,
    });

    this.medicamentosFiltrados = this.medicamentos;

    window.scrollTo({ top: 0, behavior: "smooth" });
  }

  excluirEstoque(id: number): void {
    if (!this.isAdmin) return;

    if (confirm("Deseja realmente excluir este estoque?")) {
      this.estoqueService.deleteEstoque(id).subscribe(() => {
        alert("Estoque removido!");
        this.carregarDados();
        this.filtrarEstoquePorFarmacia();
      });
    }
  }

  cancelarEdicao(): void {
    this.modoEdicao = false;
    this.estoqueSelecionado = null;
    this.estoqueForm.reset({ quantidade: 0 });
  }

  getNomeMedicamento(id: number): string {
    const med = this.medicamentos.find((m) => m.id === id);
    return med ? med.nome : "—";
  }

  getNomeFarmacia(id: number): string {
    const f = this.farmacias.find((fa) => fa.id === id);
    return f ? f.nomeFantasia : "—";
  }
}
