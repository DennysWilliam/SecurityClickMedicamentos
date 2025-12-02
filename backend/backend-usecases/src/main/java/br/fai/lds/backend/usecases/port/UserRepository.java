package br.fai.lds.backend.usecases.port;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.domain.UserModel;

import java.util.List;
import java.util.Map;

public interface UserRepository {
    UserModel findById(final int id);

    List<UserModel> findAll();

    List<UserModel> findByCriteria(Map<String, String> criteria);

    boolean update(final UserModel userModel);

    boolean deleteById(final int id);

    boolean create(final UserModel userModel);

}
