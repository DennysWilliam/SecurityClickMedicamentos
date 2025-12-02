package br.fai.lds.frontend.usecases.user;

import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.port.RestService;

import java.util.List;

public class ShowAllUsers {
    private final RestService<UserModel> restService;

    public ShowAllUsers(RestService<UserModel> restService) {
        this.restService = restService;
    }

    public List<UserModel> showAllUsers(){
        final String resource = "/user/all";
        final List<UserModel> userModels = restService.get(resource);
        return userModels;
    }
}
