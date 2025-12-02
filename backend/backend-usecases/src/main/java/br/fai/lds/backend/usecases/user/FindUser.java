package br.fai.lds.backend.usecases.user;

import br.fai.lds.backend.usecases.exception.InvalidIdException;
import br.fai.lds.backend.usecases.exception.NotFoundException;
import br.fai.lds.backend.usecases.port.UserRepository;
import br.fai.lds.domain.UserModel;

import java.util.List;

public class FindUser {
    private final UserRepository userRepository;

    public FindUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> find(){
        final List<UserModel> user = userRepository.findAll();
        if(user == null){
            return null;
        }
        return user;
    }
    public UserModel find(final int id){
        if(id < 0){
            throw new InvalidIdException();
        }
        final UserModel user = userRepository.findById(id);
        if(userRepository == null){
            final String message = "O id " + id + " nao foi encontrado";
            throw new NotFoundException(message);
        }

        return user;
    }
}
