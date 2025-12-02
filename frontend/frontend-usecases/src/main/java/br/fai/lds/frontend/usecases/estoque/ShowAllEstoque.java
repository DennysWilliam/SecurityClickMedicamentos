package br.fai.lds.frontend.usecases.estoque;

import br.fai.lds.domain.EstoqueModel;
import br.fai.lds.frontend.usecases.port.RestService;

import java.util.List;

public class ShowAllEstoques {

    private final RestService<EstoqueModel> restService;

    public ShowAllEstoques(RestService<EstoqueModel> restService) {
        this.restService = restService;
    }

    public List<EstoqueModel> showAllEstoques(){
        final String resource = "/estoque/all";
        final List<EstoqueModel> estoqueModels = restService.get(resource);
        return estoqueModels;
    }

    public List<EstoqueModel> showAllEstoquesByFarmacia(final int farmaciaId){
        final String resource = "/estoque/allbyfarmacia/" + farmaciaId;
        final List<EstoqueModel> estoqueModels = restService.get(resource);
        return estoqueModels;
    }
}
