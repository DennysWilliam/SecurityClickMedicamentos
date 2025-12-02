package br.fai.lds.frontend.usecases.favorito;

import br.fai.lds.domain.FavoritoModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class FindFavoritoById {

    private final RestService<FavoritoModel> restService;

    public FindFavoritoById(final RestService<FavoritoModel> restService) {
        this.restService = restService;
    }

    public FavoritoModel findById(final int id){
        final String resource = "/favorito/find/" + id;
        FavoritoModel byId = restService.getById(resource, FavoritoModel.class);
        return byId;
    }
}
