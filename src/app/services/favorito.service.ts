import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Favorito } from "../models/favorito.model";

@Injectable({
  providedIn: "root",
})
export class FavoritoService {
  private apiUrl = "http://localhost:8080/api/favorito";

  constructor(private http: HttpClient) {}

  getFavoritosByUsuario(idUsuario: number): Observable<Favorito[]> {
    return this.http.get<Favorito[]>(`${this.apiUrl}/usuario/${idUsuario}`);
  }

  addFavorito(usuarioId: number, medicamentoId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/create`, {
      idUsuario: usuarioId,
      idMedicamento: medicamentoId,
    });
  }

  deleteFavorito(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  deleteFavoritoByUserAndMedicamento(
    usuarioId: number,
    medicamentoId: number
  ): Observable<void> {
    return this.http.delete<void>(
      `${this.apiUrl}/usuario/${usuarioId}/medicamento/${medicamentoId}`
    );
  }
}
