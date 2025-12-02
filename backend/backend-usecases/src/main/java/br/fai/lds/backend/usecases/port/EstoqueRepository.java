package br.fai.lds.backend.usecases.port;

import br.fai.lds.domain.EstoqueModel;
import java.util.List;

public interface EstoqueRepository {
    List<EstoqueModel> findAll();
    EstoqueModel findById(Long id);
    List<EstoqueModel> findByFarmaciaId(Long farmaciaId);
    EstoqueModel create(EstoqueModel estoque);
    boolean update(EstoqueModel estoque);
    boolean delete(Long id);
    Integer getQuantidadePorMedicamento(Long medicamentoId);
}
