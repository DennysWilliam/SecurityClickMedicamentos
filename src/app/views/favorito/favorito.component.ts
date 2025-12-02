import { Component, OnInit } from "@angular/core";
import { FavoritoService } from "../../services/favorito.service";
import { Favorito } from "../../models/favorito.model";
import { getCurrentUserStoraged } from "src/app/helpers/localstorage";

@Component({
  selector: "app-favorito",
  templateUrl: "./favorito.component.html",
  styleUrls: ["./favorito.component.css"],
})
export class FavoritoComponent implements OnInit {
  favoritos: Favorito[] = [];
  errorMessage = "";
  loading = true;

  constructor(private favoritoService: FavoritoService) {}

  ngOnInit(): void {
    const user = getCurrentUserStoraged();
    if (!user) {
      this.errorMessage = "Usuário não logado";
      this.loading = false;
      return;
    }

    const userId = user.id;
    this.favoritoService.getFavoritosByUsuario(userId).subscribe({
      next: (data: Favorito[]) => {
        this.favoritos = data;
        this.loading = false;
      },
      error: (err) => {
        console.error("Erro ao carregar favoritos:", err);
        this.errorMessage = "Erro ao carregar favoritos";
        this.loading = false;
      },
    });
  }

  removerFavorito(id: number): void {
    if (confirm("Remover dos favoritos?")) {
      this.favoritoService.deleteFavorito(id).subscribe({
        next: () => {
          this.favoritos = this.favoritos.filter((f) => f.id !== id);
        },
        error: (err) => {
          console.error("Erro ao remover favorito:", err);
        },
      });
    }
  }
}
