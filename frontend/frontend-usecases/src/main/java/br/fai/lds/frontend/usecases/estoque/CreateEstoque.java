package br.fai.lds.frontend.usecases.estoque;

import br.fai.lds.domain.EstoqueModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class CreateEstoque {

    private final RestService<EstoqueModel> restService;

    public CreateEstoque(RestService<EstoqueModel> restService) {
        this.restService = restService;
    }

    public int createEstoque(final EstoqueModel estoqueModel){
        final String resource = "/estoque/add";
        return restService.post(resource, estoqueModel);
    }
}
