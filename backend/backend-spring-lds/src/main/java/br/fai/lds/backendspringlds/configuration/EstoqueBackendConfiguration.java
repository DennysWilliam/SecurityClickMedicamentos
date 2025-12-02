package br.fai.lds.backendspringlds.configuration;

import br.fai.lds.backend.implementation.repository.EstoqueDaoPostgres;
import br.fai.lds.backend.implementation.repository.MedicationDaoPostgres;
import br.fai.lds.backend.usecases.estoque.FindEstoque;
import br.fai.lds.backend.usecases.estoque.UpdateEstoque;
import br.fai.lds.backend.usecases.port.EstoqueRepository;
import br.fai.lds.backend.usecases.port.MedicationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EstoqueBackendConfiguration {

    @Bean
    public EstoqueRepository estoqueRepository() {
        return new EstoqueDaoPostgres();
    }

    @Bean
    public MedicationRepository medicationRepository() {
        return new MedicationDaoPostgres();
    }

    @Bean
    public FindEstoque findEstoque(EstoqueRepository estoqueRepo, MedicationRepository medicationRepo) {
        return new FindEstoque(estoqueRepo, medicationRepo);
    }

    @Bean
    public UpdateEstoque updateEstoque(EstoqueRepository repo) {
        return new UpdateEstoque(repo);
    }
}
