package br.fai.lds.backend.implementation.repository;

import br.fai.lds.backend.implementation.repository.connection.ConnectionFactory;
import br.fai.lds.backend.usecases.port.MedicationRepository;
import br.fai.lds.domain.MedicationModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.Timestamp;

public class MedicationDaoPostgres implements MedicationRepository {

    @Override
    public List<MedicationModel> findAll() {
        List<MedicationModel> medications = new ArrayList<>();

        String sql = "SELECT m.id AS id_medicamento, nome, principio_ativo, descricao, categoria, m.data_cadastro AS data_cadastro_medicamento, f.nome_fantasia FROM medicamento m LEFT JOIN farmacia f ON m.id_farmacia = f.id;";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                MedicationModel med = new MedicationModel();
                med.setId(resultSet.getLong("id_medicamento"));
                med.setNome(resultSet.getString("nome"));
                med.setPrincipioAtivo(resultSet.getString("principio_ativo"));
                med.setDescricao(resultSet.getString("descricao"));
                med.setCategoria(resultSet.getString("categoria"));
                med.setData_cadastro(resultSet.getTimestamp("data_cadastro_medicamento"));
                med.setFarmaciaNome(resultSet.getString("nome_fantasia"));

                medications.add(med);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar medicamentos", e);
        }

        return medications;
    }

    @Override
    public MedicationModel findById(int id) {
        MedicationModel med = null;
        String sql = "SELECT id, nome, principio_ativo, descricao, categoria, data_cadastro " +
                "FROM medicamento WHERE id = ?;";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    med = new MedicationModel();
                    med.setId(rs.getLong("id"));
                    med.setNome(rs.getString("nome"));
                    med.setPrincipioAtivo(rs.getString("principio_ativo"));
                    med.setDescricao(rs.getString("descricao"));
                    med.setCategoria(rs.getString("categoria"));
                    med.setData_cadastro(rs.getTimestamp("data_cadastro"));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar medicamento por ID", e);
        }

        return med;
    }

    @Override
    public int create(MedicationModel medication) {
        String sql = "INSERT INTO medicamento (nome, principio_ativo, descricao, categoria, data_cadastro) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, medication.getNome());
            ps.setString(2, medication.getPrincipioAtivo());
            ps.setString(3, medication.getDescricao());
            ps.setString(4, medication.getCategoria());

            if (medication.getData_cadastro() == null) {
                ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            } else {
                ps.setTimestamp(5, medication.getData_cadastro());
            }

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar medicamento, nenhuma linha afetada.");
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    medication.setId(id);
                    return (int) id;
                } else {
                    throw new SQLException("Falha ao criar medicamento, ID não obtido.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar medicamento", e);
        }
    }


    @Override
    public boolean update(MedicationModel medication) {
        String sql = "UPDATE medicamento SET nome = ?, principio_ativo = ?, descricao = ?, categoria = ?, data_cadastro = ? WHERE id = ?";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, medication.getNome());
            ps.setString(2, medication.getPrincipioAtivo());
            ps.setString(3, medication.getDescricao());
            ps.setString(4, medication.getCategoria());
            ps.setTimestamp(5, medication.getData_cadastro());
            ps.setLong(6, medication.getId());

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar medicamento", e);
        }
    }

    @Override
    public void updatePharmacies() {

    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM medicamento WHERE id = ?";

        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar medicamento", e);
        }
    }

    @Override
    public List<MedicationModel> findAll(int id) {
        return List.of();
    }

    @Override
    public List<MedicationModel> findByCriteria(Map<String, String> criteria) {
        return List.of();
    }


}
