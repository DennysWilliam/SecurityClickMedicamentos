package br.fai.lds.frontend.usecases.port;

import br.fai.lds.domain.UserModel;

public interface AccountRestService {
    UserModel validateCredentials(final String email, final String password);

    UserModel getUser();

    boolean delete();
}
