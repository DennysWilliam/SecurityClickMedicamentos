import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Estoque } from "../domain/model/estoque.model";

@Injectable({
  providedIn: "root",
})
export class EstoqueService {
  private apiUrl = "http://localhost:8080/api/estoques";

  constructor(private http: HttpClient) {}

  getAllEstoques(): Observable<Estoque[]> {
    return this.http.get<Estoque[]>(`${this.apiUrl}`);
  }

  getEstoqueByFarmaciaId(farmaciaId: number): Observable<Estoque[]> {
    return this.http.get<Estoque[]>(
      `${this.apiUrl}/farmacia/${farmaciaId}/disponibilidade`
    );
  }
  getQuantidadePorMedicamento(medicamentoId: number): Observable<number> {
    return this.http.get<number>(
      `${this.apiUrl}/medicamento/${medicamentoId}/quantidade`
    );
  }

  createEstoque(estoque: Estoque): Observable<Estoque> {
    return this.http.post<Estoque>(`${this.apiUrl}`, estoque);
  }

  updateEstoque(estoque: Estoque): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${estoque.id}`, estoque);
  }

  deleteEstoque(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
