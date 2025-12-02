package br.fai.lds.backend.usecases.estoque;

import br.fai.lds.backend.usecases.port.EstoqueRepository;
import br.fai.lds.domain.EstoqueModel;

public class UpdateEstoque {

    private final EstoqueRepository repository;

    public UpdateEstoque(EstoqueRepository repository) {
        this.repository = repository;
    }

    public EstoqueModel create(EstoqueModel estoque) {
        return repository.create(estoque);
    }

    public boolean update(EstoqueModel estoque) {
        return repository.update(estoque);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }
}
