import { Component, OnInit } from "@angular/core";
import { MedicationService } from "src/app/services/medication.service";
import { Medication } from "src/app/domain/model/medication.model";

@Component({
  selector: "app-medication",
  templateUrl: "./medication.component.html",
  styleUrls: ["./medication.component.css"],
})
export class MedicationComponent implements OnInit {
  medications: Medication[] = [];
  loading: boolean = true;
  error: string = "";

  constructor(private medicationService: MedicationService) {}

  ngOnInit(): void {
    this.fetchMedications();
  }

  fetchMedications(): void {
    this.medicationService.getAllMedications().subscribe({
      next: (data: Medication[]) => {
        this.medications = data;
        this.loading = false;
      },
      error: () => {
        this.error = "Erro ao carregar os medicamentos";
        this.loading = false;
      },
    });
  }
}
