package br.fai.lds.frontend.usecases.favorito;

import br.fai.lds.domain.FavoritoModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class CreateFavorito {

    private final RestService<FavoritoModel> restService;

    public CreateFavorito(RestService<FavoritoModel> restService) {
        this.restService = restService;
    }

    public int createFavorito(final FavoritoModel favoritoModel){
        final String resource = "/favorito/add";
        return restService.post(resource, favoritoModel);
    }
}
