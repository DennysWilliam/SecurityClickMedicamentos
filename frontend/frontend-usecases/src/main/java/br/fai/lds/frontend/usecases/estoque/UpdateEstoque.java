package br.fai.lds.frontend.usecases.estoque;

import br.fai.lds.domain.EstoqueModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class UpdateEstoque {

    private final RestService<EstoqueModel> restService;

    public UpdateEstoque(final RestService<EstoqueModel> restService) {
        this.restService = restService;
    }

    public boolean updateEstoque(final EstoqueModel estoqueModel){
        final String resource = "/estoque/update";
        boolean estoque = restService.put(resource, estoqueModel);
        return estoque;
    }
}
