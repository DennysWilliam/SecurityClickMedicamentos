import { Component, OnInit } from "@angular/core";
import { PharmacyService } from "../../services/pharmacy.service";
import { Pharmacy } from "../../domain/model/pharmacy.model";
import { Router } from "@angular/router";
import { getCurrentUserStoraged } from "src/app/helpers/localstorage";

@Component({
  selector: "app-pharmacies",
  templateUrl: "./pharmacy.component.html",
  styleUrls: ["./pharmacy.component.css"],
})
export class PharmaciesComponent implements OnInit {
  pharmacies: Pharmacy[] = [];
  isLoading = false;
  selectedPharmacy: Pharmacy | null = null;
  showForm = false;
  isAdmin = false;

  constructor(
    private router: Router,
    private pharmacyService: PharmacyService
  ) {}

  ngOnInit(): void {
    this.loadPharmacies();
    this.isAdmin = this.checkIsAdmin();
  }

  onLogout(): void {
    localStorage.removeItem("user");
    this.router.navigate(["/login"]);
  }

  checkIsAdmin(): boolean {
    const user = getCurrentUserStoraged();
    const isAdmin = user.isAdmin;
    return isAdmin;
  }

  loadPharmacies(): void {
    this.isLoading = true;

    this.pharmacyService.getAllPharmacies().subscribe({
      next: (pharmacies) => {
        console.log("Farmácias carregadas:", pharmacies);
        this.pharmacies = pharmacies;
        this.isLoading = false;
      },
      error: (error) => {
        console.error("Erro ao carregar farmácias:", error);
        this.isLoading = false;
      },
    });
  }

  onEdit(pharmacy: Pharmacy): void {
    this.selectedPharmacy = { ...pharmacy };
    this.showForm = true;
  }

  onDelete(id: number): void {
    if (confirm("Tem certeza que deseja excluir esta farmácia?")) {
      this.pharmacyService.deletePharmacy(id).subscribe({
        next: () => {
          this.loadPharmacies();
        },
        error: (error) => {
          console.error("Erro ao excluir farmácia:", error);
        },
      });
    }
  }

  onSave(pharmacy: Pharmacy): void {
    if (pharmacy.id) {
      this.pharmacyService.updatePharmacy(pharmacy.id, pharmacy).subscribe({
        next: () => {
          this.loadPharmacies();
          this.closeForm();
        },
        error: (error) => {
          console.error("Erro ao atualizar farmácia:", error);
        },
      });
    } else {
      this.pharmacyService.createPharmacy(pharmacy).subscribe({
        next: () => {
          this.loadPharmacies();
          this.closeForm();
        },
        error: (error) => {
          console.error("Erro ao criar farmácia:", error);
        },
      });
    }
  }

  onNew(): void {
    this.selectedPharmacy = {
      id: 0,
      nomeFantasia: "",
      cnpj: "",
      endereco: "",
      cep: "",
      municipio: "",
      uf: "",
      numberMedications: 0,
      userId: 0,
      horarioFuncionamento: "",
      telefone: "",
    };
    this.showForm = true;
  }

  closeForm(): void {
    this.showForm = false;
    this.selectedPharmacy = null;
  }
}
