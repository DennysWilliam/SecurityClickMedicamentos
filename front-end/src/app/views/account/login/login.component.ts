import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl, Validators } from '@angular/forms';
import { AuthenticationService } from '../../../services/security/authentication.service';
import { UserCredentialDto } from '../../../domain/dto/user-credential-dto';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = new FormControl(null); 
  password = new FormControl(null, [
    Validators.minLength(2),
    Validators.maxLength(4), // Nota: Senha de 4 caracteres é bem curta, mas mantive sua regra
  ]);

  isLoading: boolean = false;
  isLoginIncorrect: boolean = false;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
  ) {
    console.log('login constructor');
  }

  ngOnInit(): void {
    console.log('login ngOnInit');
    this.isLoginIncorrect = false;
  }

    loginIfCredentialIsValid(){
    console.log('verificando as credenciais...');
    if(this.authenticationService.isAuthenticated()) {
      console.log('credenciais validas, navegando para tela principal');
      this.router.navigate(['']);
      return;
    }

    console.log('credenciais invalidas ou nao existem no cache');
  }

  validateFields(): boolean {
    // Verifica se os campos são válidos e não nulos
    return (this.email.valid && this.password.valid) ?? false;
  }

  login() {
    if (!this.validateFields()) return;

    console.log('botao de login clicado');

    this.isLoading = true;
    this.isLoginIncorrect = false;

    let credentials: UserCredentialDto = {
      email: this.email.value!,
      password: this.password.value!,
    };

    console.log(credentials);

    this.authenticationService.authenticate(credentials)
      .subscribe({
        next: (value: any) => {
          this.isLoading = false;
          
          console.log(value);
          const token = value.token;

          try {
            const payload = token.split('.')[1];
            console.log(token);

            const decodedPayload = atob(payload);
            const decoded = JSON.parse(decodedPayload);
            console.log(decoded);
            
            const email = decoded.sub;
            const fullname = decoded.fullname;
            const role = decoded.role;
  
            this.authenticationService.addDataToLocalStorage(email, fullname, role, token);
            this.router.navigate(['']);
          } catch (e) {
            console.error('Erro ao processar token', e);
            this.isLoginIncorrect = true;
          }
        },
        error: (err) => {
          this.isLoading = false;
          this.isLoginIncorrect = true;
          
          console.error('ocorreu um erro no servidor');
          console.error(err);
        }
      });
  }
}