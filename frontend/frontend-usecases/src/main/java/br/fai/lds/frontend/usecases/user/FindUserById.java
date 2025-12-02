package br.fai.lds.frontend.usecases.user;

import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class FindUserById {
    private final RestService<UserModel> restService;

    public FindUserById(RestService<UserModel> restService) {
        this.restService = restService;
    }

    public UserModel findById(final int id){
        final String resource = "/user/find/" + id;
        UserModel byId = restService.getById(resource, UserModel.class);
        return byId;
    }
}
