package br.fai.lds.backend.implementation.repository;

import br.fai.lds.backend.implementation.repository.connection.ConnectionFactory;
import br.fai.lds.backend.usecases.port.PharmacyRepository;
import br.fai.lds.domain.PharmacyModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PharmacyDaoPostgres implements PharmacyRepository {

    @Override
    public PharmacyModel findById(int id) {
        final PharmacyModel pharmacy = new PharmacyModel();
        String sql = "SELECT * FROM farmacia WHERE id = ?;";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pharmacy.setId(resultSet.getInt("id"));
                pharmacy.setNomeFantasia(resultSet.getString("nome_fantasia"));
                pharmacy.setCnpj(resultSet.getString("cnpj"));
                pharmacy.setCep(resultSet.getString("cep"));
                pharmacy.setEndereco(resultSet.getString("endereco"));
                pharmacy.setMunicipio(resultSet.getString("municipio"));
                pharmacy.setUf(resultSet.getString("uf"));
                pharmacy.setEmail(resultSet.getString("email"));
                pharmacy.setTelefone(resultSet.getString("telefone"));
                pharmacy.setHorarioFuncionamento(resultSet.getString("horario_funcionamento"));
                pharmacy.setSituacao(resultSet.getBoolean("situacao"));
                pharmacy.setDataCadastro(resultSet.getTimestamp("data_cadastro"));
                pharmacy.setIdUsuarioAdmin(resultSet.getInt("id_usuario_admin"));
            }

            resultSet.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pharmacy;
    }

    @Override
    public List<PharmacyModel> findAll() {
        final List<PharmacyModel> pharmacies = new ArrayList<>();
        String sql = "SELECT * FROM farmacia;";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                final PharmacyModel pharmacy = new PharmacyModel();
                pharmacy.setId(resultSet.getInt("id"));
                pharmacy.setNomeFantasia(resultSet.getString("nome_fantasia"));
                pharmacy.setCnpj(resultSet.getString("cnpj"));
                pharmacy.setCep(resultSet.getString("cep"));
                pharmacy.setEndereco(resultSet.getString("endereco"));
                pharmacy.setMunicipio(resultSet.getString("municipio"));
                pharmacy.setUf(resultSet.getString("uf"));
                pharmacy.setEmail(resultSet.getString("email"));
                pharmacy.setTelefone(resultSet.getString("telefone"));
                pharmacy.setHorarioFuncionamento(resultSet.getString("horario_funcionamento"));
                pharmacy.setDataCadastro(resultSet.getTimestamp("data_cadastro"));
                pharmacy.setIdUsuarioAdmin(resultSet.getInt("id_usuario_admin"));


                pharmacies.add(pharmacy);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pharmacies;
    }

    @Override
    public List<PharmacyModel> findAllById(final int userId) {
        final List<PharmacyModel> pharmacies = new ArrayList<>();
        String sql = "SELECT * FROM farmacia WHERE id_usuario_admin = ?;";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final PharmacyModel pharmacy = new PharmacyModel();
                pharmacy.setId(resultSet.getInt("id"));
                pharmacy.setNomeFantasia(resultSet.getString("nome_fantasia"));
                pharmacy.setCnpj(resultSet.getString("cnpj"));
                pharmacy.setCep(resultSet.getString("cep"));
                pharmacy.setEndereco(resultSet.getString("endereco"));
                pharmacy.setMunicipio(resultSet.getString("municipio"));
                pharmacy.setUf(resultSet.getString("uf"));
                pharmacy.setEmail(resultSet.getString("email"));
                pharmacy.setTelefone(resultSet.getString("telefone"));
                pharmacy.setHorarioFuncionamento(resultSet.getString("horario_funcionamento"));
                pharmacy.setDataCadastro(resultSet.getTimestamp("data_cadastro"));
                pharmacy.setIdUsuarioAdmin(resultSet.getInt("id_usuario_admin"));

                pharmacies.add(pharmacy);
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pharmacies;
    }

    @Override
    public List<PharmacyModel> findAllByCity(PharmacyModel pharmacyModel) {
        final List<PharmacyModel> pharmacies = new ArrayList<>();
        String sql = "SELECT * FROM farmacia WHERE municipio = ?;";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, pharmacyModel.getMunicipio());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final PharmacyModel pharmacy = new PharmacyModel();
                pharmacy.setId(resultSet.getInt("id"));
                pharmacy.setNomeFantasia(resultSet.getString("nome_fantasia"));
                pharmacy.setCnpj(resultSet.getString("cnpj"));
                pharmacy.setCep(resultSet.getString("cep"));
                pharmacy.setEndereco(resultSet.getString("endereco"));
                pharmacy.setMunicipio(resultSet.getString("municipio"));
                pharmacy.setUf(resultSet.getString("uf"));
                pharmacy.setEmail(resultSet.getString("email"));
                pharmacy.setTelefone(resultSet.getString("telefone"));
                pharmacy.setHorarioFuncionamento(resultSet.getString("horario_funcionamento"));
                pharmacy.setDataCadastro(resultSet.getTimestamp("data_cadastro"));
                pharmacy.setIdUsuarioAdmin(resultSet.getInt("id_usuario_admin"));

                pharmacies.add(pharmacy);
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pharmacies;
    }

    @Override
    public List<PharmacyModel> findByCriteria(Map<String, String> criteria) {
        return null; // implementar se precisar
    }

    @Override
    public boolean update(PharmacyModel pharmacy) {
        String sql = "UPDATE farmacia SET nome_fantasia = ?, cnpj = ?, cep = ?, endereco = ?, municipio = ?, uf = ?, email = ?, telefone = ?, horario_funcionamento = ?, situacao = ?, id_usuario_admin = ? WHERE id = ?;";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, pharmacy.getNomeFantasia());
            preparedStatement.setString(2, pharmacy.getCnpj());
            preparedStatement.setString(3, pharmacy.getCep());
            preparedStatement.setString(4, pharmacy.getEndereco());
            preparedStatement.setString(5, pharmacy.getMunicipio());
            preparedStatement.setString(6, pharmacy.getUf());
            preparedStatement.setString(7, pharmacy.getEmail());
            preparedStatement.setString(8, pharmacy.getTelefone());
            preparedStatement.setString(9, pharmacy.getHorarioFuncionamento());
            preparedStatement.setInt(10, pharmacy.getIdUsuarioAdmin());
            preparedStatement.setInt(11, pharmacy.getId());

            preparedStatement.executeUpdate();
            return true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM farmacia WHERE id = ?;";

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
    public int create(PharmacyModel pharmacy) {
        String sql = "INSERT INTO farmacia (nome_fantasia, cnpj, cep, endereco, municipio, uf, email, telefone, horario_funcionamento, situacao, data_cadastro, id_usuario_admin) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, pharmacy.getNomeFantasia());
            preparedStatement.setString(2, pharmacy.getCnpj());
            preparedStatement.setString(3, pharmacy.getCep());
            preparedStatement.setString(4, pharmacy.getEndereco());
            preparedStatement.setString(5, pharmacy.getMunicipio());
            preparedStatement.setString(6, pharmacy.getUf());
            preparedStatement.setString(7, pharmacy.getEmail());
            preparedStatement.setString(8, pharmacy.getTelefone());
            preparedStatement.setString(9, pharmacy.getHorarioFuncionamento());
            preparedStatement.setBoolean(10, pharmacy.isSituacao());
            preparedStatement.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
            preparedStatement.setInt(12, pharmacy.getIdUsuarioAdmin());

            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    pharmacy.setId(id);
                    return id;
                }
            }

            return -1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
