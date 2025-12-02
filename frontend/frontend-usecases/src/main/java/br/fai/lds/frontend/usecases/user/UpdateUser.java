package br.fai.lds.frontend.usecases.user;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class UpdateUser {
    private final RestService<UserModel> restService;

    public UpdateUser(final RestService<UserModel> restService) {
        this.restService = restService;
    }

    public boolean updateUser(final UserModel userModel){
        final String resource = "/user/update";
        boolean user = restService.put(resource, userModel);
        return user;
    }

    public boolean updateUserBalance(final UserModel userModel){
        final String resource = "/user/updatebalance";
        boolean user = restService.put(resource, userModel);
        return user;
    }
    public boolean decreaseBalance(final UserModel userModel){
        final String resource = "/user/decreasebalance";
        boolean user = restService.put(resource, userModel);
        return user;
    }
}
