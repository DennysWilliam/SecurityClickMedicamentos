package br.fai.lds.backendspringlds.configuration;

import br.fai.lds.backend.implementation.repository.LoginDaoPostgres;
import br.fai.lds.backend.usecases.authentication.LoginUseCase;
import br.fai.lds.backend.usecases.authentication.LogoutUseCase;
import br.fai.lds.backend.usecases.port.LoginRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationBackendAppConfiguration {
    @Bean
    public LoginUseCase loginUseCase () {
        final LoginRepository loginRepository = new LoginDaoPostgres();
        return new LoginUseCase(loginRepository);
    }

    @Bean
    public LogoutUseCase logoutUseCase(){
        final LoginRepository loginRepository = new LoginDaoPostgres();
        return new LogoutUseCase(loginRepository);
    }
}
