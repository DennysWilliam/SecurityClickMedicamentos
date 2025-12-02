package br.fai.lds.backend.implementation.repository;

import br.fai.lds.backend.usecases.port.EstoqueRepository;
import br.fai.lds.domain.EstoqueModel;
import br.fai.lds.backend.implementation.repository.connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDaoPostgres implements EstoqueRepository {

    private static final String TABLE = "estoque";

    @Override
    public List<EstoqueModel> findAll() {
        List<EstoqueModel> lista = new ArrayList<>();
        String sql = "SELECT id, id_farmacia, id_medicamento, quantidade FROM " + TABLE;
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                EstoqueModel e = new EstoqueModel();
                e.setId(rs.getLong("id"));
                e.setFarmaciaId(rs.getLong("id_farmacia"));
                e.setMedicamentoId(rs.getLong("id_medicamento"));
                e.setQuantidade(rs.getInt("quantidade"));
                lista.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public EstoqueModel findById(Long id) {
        String sql = "SELECT id, id_farmacia, id_medicamento, quantidade FROM " + TABLE + " WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    EstoqueModel e = new EstoqueModel();
                    e.setId(rs.getLong("id"));
                    e.setFarmaciaId(rs.getLong("id_farmacia"));
                    e.setMedicamentoId(rs.getLong("id_medicamento"));
                    e.setQuantidade(rs.getInt("quantidade"));
                    return e;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<EstoqueModel> findByFarmaciaId(Long farmaciaId) {
        List<EstoqueModel> lista = new ArrayList<>();
        String sql = "SELECT id, id_farmacia, id_medicamento, quantidade FROM " + TABLE + " WHERE id_farmacia = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(1, farmaciaId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    EstoqueModel e = new EstoqueModel();
                    e.setId(rs.getLong("id"));
                    e.setFarmaciaId(rs.getLong("id_farmacia"));
                    e.setMedicamentoId(rs.getLong("id_medicamento"));
                    e.setQuantidade(rs.getInt("quantidade"));
                    lista.add(e);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public EstoqueModel create(EstoqueModel estoque) {
        String sql = "INSERT INTO " + TABLE + " (id_farmacia, id_medicamento, quantidade) VALUES (?, ?, ?) RETURNING id";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(1, estoque.getFarmaciaId());
            pst.setLong(2, estoque.getMedicamentoId());
            pst.setInt(3, estoque.getQuantidade());

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    estoque.setId(rs.getLong("id"));
                    return estoque;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(EstoqueModel estoque) {
        String sql = "UPDATE " + TABLE + " SET id_farmacia = ?, id_medicamento = ?, quantidade = ? WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(1, estoque.getFarmaciaId());
            pst.setLong(2, estoque.getMedicamentoId());
            pst.setInt(3, estoque.getQuantidade());
            pst.setLong(4, estoque.getId());

            return pst.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM " + TABLE + " WHERE id = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(1, id);
            return pst.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Integer getQuantidadePorMedicamento(Long medicamentoId) {
        String sql = "SELECT COALESCE(SUM(quantidade), 0) AS total FROM " + TABLE + " WHERE id_medicamento = ?";
        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setLong(1, medicamentoId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total");
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
