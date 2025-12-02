package br.fai.lds.backend.implementation.repository;

import br.fai.lds.backend.implementation.repository.connection.ConnectionFactory;
import br.fai.lds.backend.usecases.port.LoginRepository;
import br.fai.lds.domain.UserModel;

import java.sql.*;

public class LoginDaoPostgres implements LoginRepository {
    @Override
    public UserModel login(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "SELECT * FROM usuario ";
        sql += " WHERE ";
        sql += " email = ? AND senha = ?";


        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            final UserModel userModel;
            if(resultSet.next()){
                userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setNome(resultSet.getString("nome"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setTelefone(resultSet.getString("telefone"));
                userModel.setSenha(resultSet.getString("senha"));
                userModel.setTipo(resultSet.getString("tipo"));

            }else{
                userModel = null;
            }

            resultSet.close();
            preparedStatement.close();

            return userModel;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int create(UserModel userModel) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sql = "INSERT INTO usuario_logado(nome, username, rua, numero_rua, email, telefone, senha, usuario_id) ";
        sql += " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, userModel.getNome());
            preparedStatement.setString(2, userModel.getEndereco());
            preparedStatement.setString(3, userModel.getEmail());
            preparedStatement.setString(4, userModel.getTelefone());
            preparedStatement.setString(5, userModel.getSenha());
            preparedStatement.setInt(6, userModel.getId());

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                final int id = resultSet.getInt(1);
                userModel.setId(id);
            }

            connection.commit();

            resultSet.close();
            preparedStatement.close();

            return userModel.getId();
        } catch (Exception e) {
            if(connection != null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserModel find() {
        final UserModel userModel = new UserModel();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        final String sql = "SELECT * FROM usuario_logado";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                userModel.setId(resultSet.getInt("id"));
                userModel.setNome(resultSet.getString("nome"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setSenha(resultSet.getString("senha"));
                userModel.setTelefone( resultSet.getString("telefone"));
                userModel.setCpf(resultSet.getString("cpf"));
                userModel.setEndereco(resultSet.getString("cidade"));
                userModel.setUf(resultSet.getString("estado"));
                userModel.setTipo(resultSet.getString("tipo"));

                resultSet.close();
                preparedStatement.close();

            }else{
                return null;
            }

            return userModel;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean logout() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "DELETE FROM usuario_logado";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.execute();

            preparedStatement.close();

            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(UserModel userModel) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE usuario_logado SET nome = ?, username = ?, rua = ?, numero_rua = ?, email = ?, telefone = ?, senha = ?";
        sql += " WHERE id = ?;";

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userModel.getNome());
            preparedStatement.setString(2, userModel.getEndereco());
            preparedStatement.setString(3, userModel.getEmail());
            preparedStatement.setString(4, userModel.getTelefone());
            preparedStatement.setString(5, userModel.getSenha());
            preparedStatement.setInt(6, userModel.getId());

            preparedStatement.execute();

            preparedStatement.close();

            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
