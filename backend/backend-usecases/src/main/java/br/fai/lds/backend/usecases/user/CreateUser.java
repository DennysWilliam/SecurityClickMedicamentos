package br.fai.lds.backend.usecases.user;

import br.fai.lds.backend.usecases.port.UserRepository;
import br.fai.lds.domain.UserModel;

public class CreateUser {
    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(final UserModel userModel){
        if (userModel == null) {
            return false;
        }
        if (
                userModel.getNome().isEmpty()
                || userModel.getEmail().isEmpty()
                || userModel.getTelefone().isEmpty()){


            return false;
        }
        return userRepository.create(userModel);
    }
}
