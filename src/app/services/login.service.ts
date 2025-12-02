import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Login } from "../models/login.model";

@Injectable({
  providedIn: "root",
})
export class LoginService {
  private apiUrl = "http://localhost:8080/api/authentication/login";

  constructor(private http: HttpClient) {}

  getUserInfo(body: Login): Observable<Login[]> {
    return this.http.post<Login[]>(`${this.apiUrl}`, body);
  }

  getUser(): Login | null {
    const userData = localStorage.getItem("user");
    return userData ? JSON.parse(userData) : null;
  }

  isLoggedIn(): boolean {
    return this.getUser() !== null;
  }
}
