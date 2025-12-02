package br.fai.lds.frontend.usecases.favorito;

import br.fai.lds.domain.FavoritoModel;
import br.fai.lds.frontend.usecases.port.RestService;

import java.util.List;

public class ShowAllFavoritos {

    private final RestService<FavoritoModel> restService;

    public ShowAllFavoritos(RestService<FavoritoModel> restService) {
        this.restService = restService;
    }

    public List<FavoritoModel> showAllFavoritos(){
        final String resource = "/favorito/all";
        final List<FavoritoModel> favoritoModels = restService.get(resource);
        return favoritoModels;
    }

    public List<FavoritoModel> showAllFavoritosByUsuario(final int usuarioId){
        final String resource = "/favorito/allbyusuario/" + usuarioId;
        final List<FavoritoModel> favoritoModels = restService.get(resource);
        return favoritoModels;
    }
}
