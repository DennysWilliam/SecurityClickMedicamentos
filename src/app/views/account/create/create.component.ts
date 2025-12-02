import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { UserService } from "../../../services/user.service";
import { User } from "../../../domain/model/user";
import { Router } from "@angular/router";
import { UserRole } from "src/app/domain/model/user-role";

@Component({
  selector: "app-user-create",
  templateUrl: "create.component.html",
  styleUrls: ["create.component.css"],
})
export class UserCreateComponent {
  userForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {
    this.userForm = this.fb.group({
      cpf: [
        "",
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(11),
        ],
      ],
      nome: ["", Validators.required],
      email: ["", [Validators.required, Validators.email]],
      endereco: ["", Validators.required],
      telefone: ["", Validators.required],
      municipio: ["", Validators.required],
      uf: ["", Validators.required],
      senha: ["", [Validators.required, Validators.minLength(6)]],
    });
  }

  validateFields() : boolean {
      let isFullnameValid =  this.userForm.controls['nome'].valid;
      let isEmailValid =  this.userForm.controls['email'].valid;
      let isPasswordValid =  this.userForm.controls['senha'].valid;

      return isFullnameValid
          && isEmailValid
          && isPasswordValid

  }

  create() {
    if (!this.userForm.valid) {
      this.userForm.markAllAsTouched();
      return;
    }
    const formValues = this.userForm.value;

    const newUser: User = {
      id: 0,
      cpf: formValues.cpf,
      nome: formValues.nome,
      email: formValues.email,
      endereco: formValues.endereco,
      telefone: formValues.telefone,
      municipio: formValues.municipio,
      uf: formValues.uf.toUpperCase(),
      senha: formValues.senha,
      role: UserRole.USER,
    };

    this.userService.createUser(newUser).subscribe({
      next: (res) => {
        console.log("Usuário criado com sucesso:", res);
        alert("Usuário cadastrado!");
        this.router.navigate(["/login"]);
        this.userForm.reset();
      },
      error: (err) => {
        console.error("Erro ao criar usuário:", err);
        alert("Erro ao salvar usuário.");
      },
    });
  }

  hasError(field: string): boolean {
    const control = this.userForm.get(field);
    return !!(control && control.invalid && (control.dirty || control.touched));
  }

  getErrorMessage(field: string): string {
    const control = this.userForm.get(field);

    if (control?.hasError("required")) {
      return "Campo obrigatório";
    }
    if (control?.hasError("email")) {
      return "E-mail inválido";
    }
    if (control?.hasError("minlength")) {
      return `Mínimo de ${control.errors?.["minlength"].requiredLength} caracteres`;
    }
    if (control?.hasError("maxlength")) {
      return `Máximo de ${control.errors?.["maxlength"].requiredLength} caracteres`;
    }
    return "";
  }
}
