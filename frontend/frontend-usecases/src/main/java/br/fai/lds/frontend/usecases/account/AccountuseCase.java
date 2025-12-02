package br.fai.lds.frontend.usecases.account;

import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.port.AccountRestService;

public class AccountuseCase {
    private final AccountRestService accountRestService;
        public AccountuseCase(AccountRestService accountRestService) {
        this.accountRestService = accountRestService;
    }

    public UserModel login (final String email, final String password){
        if(email == null){
            return null;
        }

        if(password == null || password.length() <4){
            return null;
        }

        UserModel user = accountRestService.validateCredentials(email, password);
        return user;
    }
}

