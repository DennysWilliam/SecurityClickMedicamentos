import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Medication } from "../models/medication.model";

@Injectable({
  providedIn: "root",
})
export class MedicationService {
  private apiUrl = "http://localhost:8080/api/medication";

  constructor(private http: HttpClient) {}

  getAllMedications(): Observable<Medication[]> {
    return this.http.get<Medication[]>(`${this.apiUrl}/all`);
  }

  getMedicationById(id: number): Observable<Medication> {
    return this.http.get<Medication>(`${this.apiUrl}/${id}`);
  }

  createMedication(medication: Partial<Medication>): Observable<number> {
    return this.http.post<number>(
      `${this.apiUrl}/create-medication`,
      medication
    );
  }

  updateMedication(medication: Medication): Observable<boolean> {
    return this.http.put<boolean>(`${this.apiUrl}/update`, medication);
  }

  deleteMedication(id: number): Observable<boolean> {
    return this.http.delete<boolean>(`${this.apiUrl}/delete/${id}`);
  }
}
