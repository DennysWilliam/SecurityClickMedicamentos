package br.fai.lds.frontend.usecases.favorito;

import br.fai.lds.domain.FavoritoModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class UpdateFavorito {

    private final RestService<FavoritoModel> restService;

    public UpdateFavorito(final RestService<FavoritoModel> restService) {
        this.restService = restService;
    }

    public boolean updateFavorito(final FavoritoModel favoritoModel){
        final String resource = "/favorito/update";
        boolean favorito = restService.put(resource, favoritoModel);
        return favorito;
    }
}
