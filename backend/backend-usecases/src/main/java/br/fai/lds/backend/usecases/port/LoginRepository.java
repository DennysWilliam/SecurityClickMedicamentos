package br.fai.lds.backend.usecases.port;

import br.fai.lds.domain.UserModel;

import java.util.List;

public interface LoginRepository {
    UserModel login(final String email, final String password);
    int create(final UserModel userModel);
    UserModel find();
    boolean logout();
    boolean update(final UserModel userModel);
}
