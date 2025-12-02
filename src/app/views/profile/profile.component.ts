import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { User } from "../../models/user.model";
import { UserService } from "../../services/user.service";
import { getCurrentUserStoraged } from "src/app/helpers/localstorage";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html",
  styleUrls: ["./profile.component.css"],
})
export class ProfileComponent implements OnInit {
  user: User | null = null;
  isLoading = true;
  error: string | null = null;
  storagedUser: any;
  isAdmin: boolean = false;

  isEditing: boolean = false;
  profileForm!: FormGroup;

  constructor(
    private router: Router,
    private userService: UserService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.storagedUser = getCurrentUserStoraged();
    if (!this.storagedUser) {
      this.router.navigate(["/login"]);
      return;
    }
    this.loadUserProfile();
  }

  private initForm(user: User): void {
    this.profileForm = this.fb.group({
      nome: [user.nome, Validators.required],
      email: [user.email, [Validators.required, Validators.email]],
      telefone: [user.telefone, Validators.required],
      endereco: [user.endereco, Validators.required],
      municipio: [user.municipio, Validators.required],
      uf: [user.uf, [Validators.required, Validators.maxLength(2)]],
      cpf: [user.cpf],
      tipo: [user.tipo],
      id: [user.id],
    });
  }

  loadUserProfile(): void {
    this.isLoading = true;
    this.error = null;

    this.userService.getUserById(this.storagedUser.id).subscribe({
      next: (userData) => {
        this.user = userData;
        this.isAdmin = userData.tipo === "A";
        this.isLoading = false;
        this.initForm(userData);
      },
      error: () => {
        this.error =
          "Não foi possível carregar os dados do perfil. Tente novamente mais tarde.";
        this.isLoading = false;
      },
    });
  }

  onEdit(): void {
    if (this.user) {
      this.isEditing = true;
      console.log("Entrou no modo de edição.");
    }
  }

  onCancelEdit(): void {
    this.isEditing = false;
    if (this.user) {
      this.profileForm.reset(this.user);
    }
  }

  onSave(): void {
    if (this.profileForm.invalid || !this.user) {
      console.error("Formulário inválido ou usuário não carregado.");
      return;
    }

    this.isLoading = true;

    const updatedUser: User = {
      ...this.user,
      ...this.profileForm.value,
    };

    this.userService.updateUser(updatedUser.id, updatedUser).subscribe({
      next: (responseUser) => {
        this.user = responseUser;
        this.isLoading = false;
        this.isEditing = false;
        console.log("Perfil atualizado com sucesso!");
      },
      error: (err) => {
        console.error("Erro ao salvar o perfil:", err);
        this.error = "Falha ao atualizar o perfil. Tente novamente.";
        this.isLoading = false;
      },
    });
  }

  onLogout(): void {
    localStorage.removeItem("user");
    this.router.navigate(["/login"]);
  }
}
