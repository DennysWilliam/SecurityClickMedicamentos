package br.fai.lds.backend.usecases.port;

public interface PasswordEncoder {

    String encode(final String password);

}
