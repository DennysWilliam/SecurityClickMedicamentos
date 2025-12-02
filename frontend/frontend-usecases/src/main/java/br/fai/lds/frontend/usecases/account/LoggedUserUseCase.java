package br.fai.lds.frontend.usecases.account;

import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.port.AccountRestService;

import java.util.List;

public class LoggedUserUseCase {
    private final AccountRestService accountRestService;

    public LoggedUserUseCase(AccountRestService accountRestService) {
        this.accountRestService = accountRestService;
    }


    public UserModel showLoggedUser(){
        final UserModel userModel = accountRestService.getUser();
        if(userModel == null){
            return null;
        }
        return userModel;
    }
}
