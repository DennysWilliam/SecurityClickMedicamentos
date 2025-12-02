package br.fai.lds.frontend.usecases.favorito;

import br.fai.lds.domain.FavoritoModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class DeleteFavorito {

    private final RestService<FavoritoModel> restService;

    public DeleteFavorito(RestService<FavoritoModel> restService) {
        this.restService = restService;
    }

    public boolean deleteFavorito(final int id){
        if(id <= 0){
            return false;
        }
        final String resource = "/favorito/delete/" + id;
        return restService.delete(resource);
    }
}
