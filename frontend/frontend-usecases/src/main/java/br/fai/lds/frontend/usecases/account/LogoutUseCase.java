package br.fai.lds.frontend.usecases.account;

import br.fai.lds.frontend.usecases.port.AccountRestService;

public class LogoutUseCase {
    private final AccountRestService accountRestService;

    public LogoutUseCase(AccountRestService accountRestService) {
        this.accountRestService = accountRestService;
    }

    public boolean logout(){
        return accountRestService.delete();
    }
}
