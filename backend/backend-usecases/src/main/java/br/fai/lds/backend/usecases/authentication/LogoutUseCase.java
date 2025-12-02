package br.fai.lds.backend.usecases.authentication;

import br.fai.lds.backend.usecases.port.LoginRepository;

public class LogoutUseCase {
    private final LoginRepository loginRepository;

    public LogoutUseCase(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public boolean logout(){
        boolean logout = loginRepository.logout();
        return logout;
    }
}
