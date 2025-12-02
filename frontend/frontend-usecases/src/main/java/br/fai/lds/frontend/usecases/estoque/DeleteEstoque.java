package br.fai.lds.frontend.usecases.estoque;

import br.fai.lds.domain.EstoqueModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class DeleteEstoque {

    private final RestService<EstoqueModel> restService;

    public DeleteEstoque(RestService<EstoqueModel> restService) {
        this.restService = restService;
    }

    public boolean deleteEstoque(final int id){
        if(id <= 0){
            return false;
        }
        final String resource = "/estoque/delete/" + id;
        return restService.delete(resource);
    }
}
