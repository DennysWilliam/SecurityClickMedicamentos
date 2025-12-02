package br.fai.lds.backendspringlds.configuration;

import br.fai.lds.backend.implementation.repository.LoginDaoPostgres;
import br.fai.lds.backend.implementation.repository.UserDaoPostgres;
import br.fai.lds.backend.usecases.DeleteUser;
import br.fai.lds.backend.usecases.port.LoginRepository;
import br.fai.lds.backend.usecases.port.UserRepository;
import br.fai.lds.backend.usecases.user.CreateUser;
import br.fai.lds.backend.usecases.user.FindUser;
import br.fai.lds.backend.usecases.user.UpdateUser;
import br.fai.lds.backendspringlds.restcontrollers.PharmacyRestController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBackendConfiguration {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    public UserBackendConfiguration() {
        this.loginRepository = new LoginDaoPostgres();
        this.userRepository = new UserDaoPostgres();
    }
    @Bean
    public FindUser findUser(){
        return new FindUser(userRepository);
    }

    @Bean
    public CreateUser createUser(){
        return new CreateUser(userRepository);
    }

    @Bean
    public UpdateUser updateUser() {
        return new UpdateUser(userRepository, loginRepository);
    }

    @Bean
    public DeleteUser deleteUser() {
        return new DeleteUser(userRepository); // ou o DAO que você estiver usando
    }
}
