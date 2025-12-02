package br.fai.lds.frontendspringlds.configuration;

import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.port.RestService;
import br.fai.lds.frontend.usecases.user.CreateUser;
import br.fai.lds.frontend.usecases.user.FindUserById;
import br.fai.lds.frontend.usecases.user.ShowAllUsers;
import br.fai.lds.frontend.usecases.user.UpdateUser;
import br.fai.lds.frontendspringlds.controller.impl.RestApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFrontendAppConfigurator {
    @Bean
    public ShowAllUsers showAllUsers(){
        RestService<UserModel> restService = new RestApiController<>();
        return new ShowAllUsers(restService);
    }

    @Bean
    public FindUserById findUserById(){
        RestService<UserModel> restService = new RestApiController<>();
        return new FindUserById(restService);
    }

    @Bean
    public CreateUser createUser(){
        RestService<UserModel> restService = new RestApiController<>();
        return new CreateUser(restService);
    }

    @Bean
    public UpdateUser updateUser(){
        RestService<UserModel> restService = new RestApiController<>();
        return new UpdateUser(restService);
    }
}
