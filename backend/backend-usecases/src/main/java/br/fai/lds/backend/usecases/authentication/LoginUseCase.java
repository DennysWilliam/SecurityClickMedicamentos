package br.fai.lds.backend.usecases.authentication;

import br.fai.lds.backend.usecases.port.LoginRepository;
import br.fai.lds.domain.UserModel;


import java.util.List;

public class LoginUseCase {
    private final LoginRepository loginRepository;

    public LoginUseCase(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public UserModel login(final String email, final String password){
        if(email.isEmpty() || password.isEmpty() ){
            return null;
        }

        final UserModel user = loginRepository.login(email, password);
        System.out.println(user.getNome());

        if(user == null){
            return null;
        }

        UserModel userResponse = new UserModel();
        userResponse.setId(user.getId());
        userResponse.setNome(user.getNome());
        userResponse.setEmail(user.getEmail());
        System.out.println("tipo" + user.getTipo());
        userResponse.setTipo(user.getTipo());

        return userResponse;
    }

    public UserModel find(){
        final UserModel user = loginRepository.find();
        if(user == null){
            return null;
        }
        return user;
    }


}
