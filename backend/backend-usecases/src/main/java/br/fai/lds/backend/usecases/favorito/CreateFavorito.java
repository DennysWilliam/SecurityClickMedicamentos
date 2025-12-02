package br.fai.lds.backend.usecases.favorito;

import br.fai.lds.backend.usecases.port.FavoritoRepository;
import br.fai.lds.domain.FavoritoModel;

public class CreateFavorito {

    private final FavoritoRepository repository;

    public CreateFavorito(FavoritoRepository repository) {
        this.repository = repository;
    }

    public FavoritoModel create(FavoritoModel favorito) {
        return repository.create(favorito);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }

    public boolean deleteByUsuarioAndMedicamento(Long usuarioId, Long medicamentoId) {
        return repository.deleteByUsuarioAndMedicamento(usuarioId, medicamentoId);
    }
}
