package br.fai.lds.backend.usecases.favorito;

import br.fai.lds.backend.usecases.port.FavoritoRepository;
import br.fai.lds.domain.FavoritoModel;
import java.util.List;

public class FindFavorito {

    private final FavoritoRepository repository;

    public FindFavorito(FavoritoRepository repository) {
        this.repository = repository;
    }

    public List<FavoritoModel> findAll() {
        return repository.findAll();
    }

    public FavoritoModel findById(Long id) {
        return repository.findById(id);
    }

    public List<FavoritoModel> findByUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }
}
