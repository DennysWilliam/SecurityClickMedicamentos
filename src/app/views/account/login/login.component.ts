import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { LoginService } from "src/app/services/login.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent {
  loginForm: FormGroup;
  isLoading = false;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private loginService: LoginService
  ) {
    this.loginForm = this.formBuilder.group({
      usuario: ["", [Validators.required, Validators.minLength(3)]],
      senha: ["", [Validators.required, Validators.minLength(4)]],
    });
  }

  ngOnInit() {
    if (this.loginService.isLoggedIn()) {
      this.router.navigate(["/search"]);
    } else {
      this.router.navigate(["/login"]);
    }
  }

  onLogin(): void {
    if (this.loginForm.valid) {
      this.isLoading = true;

      const email = this.loginForm.get("usuario")?.value;
      const senha = this.loginForm.get("senha")?.value;

      const payload = { email, senha };

      this.loginService.getUserInfo(payload).subscribe({
        next: (data: any) => {
          const auth = {
            ...data,
            isAdmin: data.tipo === "A",
          };

          localStorage.setItem("user", JSON.stringify(auth));
          this.router.navigate(["/search"]);
          this.isLoading = false;
        },
        error: (e) => {
          alert("Acesso Negado");
          this.isLoading = false;
        },
      });
    } else {
      this.loginForm.markAllAsTouched();
    }
  }

  hasError(fieldName: string): boolean {
    const field = this.loginForm.get(fieldName);
    return !!(field && field.invalid && field.touched);
  }

  getErrorMessage(fieldName: string): string {
    const field = this.loginForm.get(fieldName);

    if (field?.errors) {
      if (field.errors["required"]) {
        return `${fieldName === "usuario" ? "Usuário" : "Senha"} é obrigatório`;
      }
      if (field.errors["minlength"]) {
        const minLength = field.errors["minlength"].requiredLength;
        return `${
          fieldName === "usuario" ? "Usuário" : "Senha"
        } deve ter pelo menos ${minLength} caracteres`;
      }
    }

    return "";
  }
}
