import { Injectable } from '@angular/core';
import { UserCredentialDto } from '../../domain/dto/user-credential-dto';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { AuthenticatedUserDto } from '../../domain/dto/authenticated-user-dto';
import { User } from '../../domain/model/user';
import { UserRole } from '../../domain/model/user-role';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  authenticate(credentials: UserCredentialDto) : Observable<any> {
    console.log('autenticando o usuario');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    const body = {
      email: credentials.email,
      password: credentials.password,
    }

   return this.http.post<any>(`${environment.authentication_api_endpoint}/authenticate`, 
    body, {headers});
  
}

  isAuthenticated(): boolean {
    let email = localStorage.getItem('email');
    if(email != null) {
      console.log(`email encontrado: ${email}`);
      return true;
    }
    return false;
  }

  addDataToLocalStorage(email: string, fullname: string, role:string, token:string){
    console.log('adicionando dados no cache...');
    localStorage.setItem('email', email);
    localStorage.setItem('fullname', fullname);
    localStorage.setItem('role', role);
    localStorage.setItem('token', token);

  }

  logout() {
    localStorage.clear();
  }

  getAuthenticatedUserEmail(): string{
    let email = localStorage.getItem("email");
    if(email == null){
      throw new Error("Dados invalidos");

    }
    return email;
  }

  getAuthenticatedUser(): AuthenticatedUserDto{
    let email = localStorage.getItem('email');
    let fullname = localStorage.getItem('fullname');
    let role = localStorage.getItem('role');
    let token = localStorage.getItem('token');


    if(email == null ||  fullname == null || role == null || token == null){
      throw new Error ('Dados invalidos');
    }

    let user: AuthenticatedUserDto ={
      email:email,
      fullname: fullname,
      role: role as UserRole,
      token: token,
    };
    return user;
  }
    getUserFromAuthentication(): User{
      let authenticatedUser: AuthenticatedUserDto = this.getAuthenticatedUser();

      let user: User = {
          nome: '',
          senha: '',
          role: authenticatedUser.role,
          email: authenticatedUser.email,
          id: 0,
          cpf: '',
          telefone: '',
          municipio: '',
          uf: '',
          endereco: ''
      }
      return user;
    }

}
