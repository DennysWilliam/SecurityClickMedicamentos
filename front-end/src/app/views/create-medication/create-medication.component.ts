import { Component } from "@angular/core";
import { MedicationService } from "src/app/services/medication.service";
import { PharmacyService } from "src/app/services/pharmacy.service";

@Component({
  selector: "app-create-medication",
  templateUrl: "./create-medication.component.html",
  styleUrls: ["./create-medication.component.css"],
})
export class CreateMedicationComponent {
  medication: any = {
    nome: "",
    principioAtivo: "",
    categoria: "",
    descricao: "",
    idFarmacia: null,
  };

  quantidade: number = 0;
  farmacias: any[] = [];

  constructor(
    private medicationService: MedicationService,
    private pharmacyService: PharmacyService
  ) {}

  ngOnInit() {
    this.loadPharmacies();
  }

  loadPharmacies() {
    this.pharmacyService.getAllPharmacies().subscribe({
      next: (data) => (this.farmacias = data),
      error: (err) => console.error("Erro ao carregar farmácias", err),
    });
  }

  createMedication() {
    this.medicationService.createMedication(this.medication).subscribe({
      next: (createdId) => {
        const estoque = {
          idMedicamento: createdId,
          idFarmacia: this.medication.idFarmacia,
          quantidade: this.quantidade,
        };

        this.medicationService.createMedication(this.medication).subscribe({
          next: () => {
            alert("Medicamento cadastrado com sucesso!");
          },
          error: (err) => console.error("Erro ao criar estoque", err),
        });
      },
      error: (err) => console.error("Erro ao criar medicamento", err),
    });
  }
}
