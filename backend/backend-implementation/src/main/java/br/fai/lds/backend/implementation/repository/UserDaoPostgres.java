package br.fai.lds.backend.implementation.repository;

import br.fai.lds.backend.implementation.repository.connection.ConnectionFactory;
import br.fai.lds.backend.usecases.port.UserRepository;
import br.fai.lds.domain.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

public class UserDaoPostgres implements UserRepository {

    @Override
    public UserModel findById(int id) {
        final UserModel userModel = new UserModel();

        String sql = "SELECT * FROM usuario WHERE id = ?;";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userModel.setId(resultSet.getInt("id"));
                userModel.setNome(resultSet.getString("nome"));
                userModel.setCpf(resultSet.getString("cpf"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setTelefone(resultSet.getString("telefone"));
                userModel.setEndereco(resultSet.getString("endereco"));
                userModel.setMunicipio(resultSet.getString("municipio"));
                userModel.setUf(resultSet.getString("uf"));
                userModel.setSenha(resultSet.getString("senha"));
                userModel.setTipo(resultSet.getString("tipo"));
                userModel.setSituacao(resultSet.getBoolean("situacao"));
                userModel.setDataCadastro(resultSet.getTimestamp("data_cadastro"));
            } else {
                return null;
            }

            resultSet.close();
            return userModel;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<UserModel> findAll() {
        final List<UserModel> users = new ArrayList<>();
        String sql = "SELECT * FROM usuario;";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                final UserModel userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setNome(resultSet.getString("nome"));
                userModel.setCpf(resultSet.getString("cpf"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setTelefone(resultSet.getString("telefone"));
                userModel.setEndereco(resultSet.getString("endereco"));
                userModel.setMunicipio(resultSet.getString("municipio"));
                userModel.setUf(resultSet.getString("uf"));
                userModel.setSenha(resultSet.getString("senha"));
                userModel.setTipo(resultSet.getString("tipo"));
                userModel.setSituacao(resultSet.getBoolean("situacao"));
                userModel.setDataCadastro(resultSet.getTimestamp("data_cadastro"));

                users.add(userModel);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    @Override
    public List<UserModel> findByCriteria(Map<String, String> criteria) {
        return null;
    }

    @Override
    public boolean update(UserModel userModel) {
        String sql = "UPDATE usuario SET nome = ?, cpf = ?, email = ?, telefone = ?, endereco = ?, municipio = ?, uf = ?, senha = ?, tipo = ?, situacao = ? WHERE id = ?;";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, userModel.getNome());
            preparedStatement.setString(2, userModel.getCpf());
            preparedStatement.setString(3, userModel.getEmail());
            preparedStatement.setString(4, userModel.getTelefone());
            preparedStatement.setString(5, userModel.getEndereco());
            preparedStatement.setString(6, userModel.getMunicipio());
            preparedStatement.setString(7, userModel.getUf());
            preparedStatement.setString(8, userModel.getSenha());
            preparedStatement.setString(9, userModel.getTipo());
            preparedStatement.setBoolean(10, userModel.isSituacao());
            preparedStatement.setInt(11, userModel.getId());

            preparedStatement.executeUpdate();
            return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?;";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean create(UserModel userModel) {
        String sql = "INSERT INTO usuario (nome, cpf, email, telefone, endereco, municipio, uf, senha, tipo, situacao, data_cadastro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, userModel.getNome());
            preparedStatement.setString(2, userModel.getCpf());
            preparedStatement.setString(3, userModel.getEmail());
            preparedStatement.setString(4, userModel.getTelefone());
            preparedStatement.setString(5, userModel.getEndereco());
            preparedStatement.setString(6, userModel.getMunicipio());
            preparedStatement.setString(7, userModel.getUf());
            preparedStatement.setString(8, userModel.getSenha());
            preparedStatement.setString(9, userModel.getTipo());
            preparedStatement.setBoolean(10, userModel.isSituacao());
            preparedStatement.setTimestamp(11, new Timestamp(System.currentTimeMillis()));

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Falha ao inserir usuário, nenhuma linha afetada.");
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    userModel.setId(id);

                    return true;
                } else {
                    throw new SQLException("Falha ao inserir usuário, ID não retornado.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
