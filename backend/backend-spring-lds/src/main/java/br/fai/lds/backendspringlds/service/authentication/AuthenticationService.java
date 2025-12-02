package br.fai.lds.backendspringlds.service.authentication;

public interface AuthenticationService {
    String authenticate(String email, String password);
}
