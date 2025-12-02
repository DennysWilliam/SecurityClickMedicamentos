package br.fai.lds.frontend.usecases.estoque;

import br.fai.lds.domain.EstoqueModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class FindEstoqueById {

    private final RestService<EstoqueModel> restService;

    public FindEstoqueById(final RestService<EstoqueModel> restService) {
        this.restService = restService;
    }

    public EstoqueModel findById(final int id){
        final String resource = "/estoque/find/" + id;
        EstoqueModel byId = restService.getById(resource, EstoqueModel.class);
        return byId;
    }
}
