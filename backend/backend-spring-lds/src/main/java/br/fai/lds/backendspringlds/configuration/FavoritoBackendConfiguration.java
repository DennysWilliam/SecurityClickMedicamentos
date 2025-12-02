package br.fai.lds.backendspringlds.configuration;

import br.fai.lds.backend.implementation.repository.FavoritoDaoPostgres;
import br.fai.lds.backend.usecases.favorito.FindFavorito;
import br.fai.lds.backend.usecases.favorito.CreateFavorito;
import br.fai.lds.backend.usecases.port.FavoritoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FavoritoBackendConfiguration {

    @Bean
    public FavoritoRepository favoritoRepository() {
        return new FavoritoDaoPostgres();
    }

    @Bean
    public FindFavorito findFavorito(FavoritoRepository repo) {
        return new FindFavorito(repo);
    }

    @Bean
    public CreateFavorito createFavorito(FavoritoRepository repo) {
        return new CreateFavorito(repo);
    }
}
