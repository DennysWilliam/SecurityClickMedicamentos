package br.fai.lds.backend.implementation.repository;

import br.fai.lds.backend.usecases.port.UserRepository;
import br.fai.lds.domain.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeUserDao implements UserRepository {

    private final Map<Integer, UserModel> database;

    public FakeUserDao() {
        this.database = new HashMap<>();
        populateData();
    }

    private static int ID = 0;

    private static int generateId() {
        ID += 1;
        return ID;
    }

    private void populateData() {
        final UserModel firstUser = new UserModel();
        firstUser.setId(generateId());
        firstUser.setNome("Dennys");
        firstUser.setSenha("12345");
        firstUser.setEmail("dennys@email.com");
        firstUser.setTelefone("35-99999-9999");
        firstUser.setEndereco("Rua do Dennys");

        final UserModel secondUser = new UserModel();
        secondUser.setId(generateId());
        secondUser.setNome("Camila");
        secondUser.setSenha("54321");
        secondUser.setEmail("camila@email.com");
        secondUser.setTelefone("35-88888-8888");
        secondUser.setEndereco("Rua do Camila");

        database.put(firstUser.getId(), firstUser);
        database.put(secondUser.getId(), secondUser);
    }

    @Override
    public UserModel findById(int id) {
        return database.get(id);
    }

    @Override
    public List<UserModel> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public List<UserModel> findByCriteria(Map<String, String> criteria) {

        return new ArrayList<>();
    }

    @Override
    public boolean update(UserModel userModel) {
        if (database.containsKey(userModel.getId())) {
            database.put(userModel.getId(), userModel);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        if (database.containsKey(id)) {
            database.remove(id);
            return true;
        }
        return false;
    }


    @Override
    public boolean create(UserModel userModel) {
        userModel.setId(generateId());
        database.put(userModel.getId(), userModel);
        return true;
    }
}
