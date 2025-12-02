package br.fai.lds.backend.usecases.port;

import br.fai.lds.domain.FavoritoModel;
import java.util.List;

public interface FavoritoRepository {

    List<FavoritoModel> findAll();

    FavoritoModel findById(Long id);

    List<FavoritoModel> findByUsuarioId(Long usuarioId);

    FavoritoModel create(FavoritoModel favorito);

    boolean delete(Long id);

    boolean deleteByUsuarioAndMedicamento(Long usuarioId, Long medicamentoId);
}
