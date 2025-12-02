package br.fai.lds.backend.usecases;

import br.fai.lds.backend.usecases.port.UserRepository;

public class DeleteUser {

    private final UserRepository userRepository;

    public DeleteUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean delete(int id) {
        return userRepository.deleteById(id);
    }
}
