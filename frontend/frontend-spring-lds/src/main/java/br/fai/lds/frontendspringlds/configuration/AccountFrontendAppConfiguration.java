package br.fai.lds.frontendspringlds.configuration;

import br.fai.lds.frontend.usecases.account.AccountuseCase;
import br.fai.lds.frontend.usecases.account.LoggedUserUseCase;
import br.fai.lds.frontend.usecases.account.LogoutUseCase;
import br.fai.lds.frontend.usecases.port.AccountRestService;
import br.fai.lds.frontendspringlds.controller.impl.AccountRestApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountFrontendAppConfiguration {
    @Bean
    public AccountuseCase accountUseCase(){
        final AccountRestService accountRestService = new AccountRestApiController();
        return new AccountuseCase(accountRestService);
    }

    @Bean
    public LoggedUserUseCase loggedUserUseCase(){
        final AccountRestService accountRestService = new AccountRestApiController();
        return new LoggedUserUseCase(accountRestService);
    }

    @Bean
    public LogoutUseCase logoutUseCase(){
        final AccountRestService accountRestService = new AccountRestApiController();
        return new LogoutUseCase(accountRestService);
    }
}
