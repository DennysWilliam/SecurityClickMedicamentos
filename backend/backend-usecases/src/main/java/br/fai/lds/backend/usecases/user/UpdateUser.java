package br.fai.lds.backend.usecases.user;

import br.fai.lds.backend.usecases.port.LoginRepository;
import br.fai.lds.backend.usecases.port.UserRepository;
import br.fai.lds.domain.UserModel;

public class UpdateUser {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    public UpdateUser(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    public boolean update(final UserModel userModel){
        final UserModel bYId = userRepository.findById(userModel.getId());
        bYId.setCpf(userModel.getCpf());
        bYId.setNome(userModel.getNome());
        bYId.setTipo(userModel.getTipo());
        bYId.setEndereco(userModel.getEndereco());
        bYId.setUf(userModel.getUf());
        bYId.setEmail(userModel.getEmail());
        bYId.setTelefone(userModel.getTelefone());



        return userRepository.update(bYId);
    }
    public boolean updateBalance(final UserModel userModel){
        final UserModel bYId = userRepository.findById(userModel.getId());

        boolean updatedPharmacy = userRepository.update(bYId);
        loginRepository.update(bYId);


        return updatedPharmacy;
    }


}
