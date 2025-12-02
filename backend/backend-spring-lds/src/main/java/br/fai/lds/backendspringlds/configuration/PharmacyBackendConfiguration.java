package br.fai.lds.backendspringlds.configuration;

import br.fai.lds.backend.implementation.repository.PharmacyDaoPostgres;
import br.fai.lds.backend.implementation.repository.MedicationDaoPostgres;
import br.fai.lds.backend.usecases.pharmacy.CreatePharmacy;
import br.fai.lds.backend.usecases.pharmacy.DeletePharmacy;
import br.fai.lds.backend.usecases.pharmacy.FindPharmacy;
import br.fai.lds.backend.usecases.pharmacy.UpdatePharmacy;
import br.fai.lds.backend.usecases.port.PharmacyRepository;
import br.fai.lds.backend.usecases.port.MedicationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PharmacyBackendConfiguration {

    private final PharmacyRepository pharmacyRepository;
    private final MedicationRepository medicationRepository;


    public PharmacyBackendConfiguration() {
        this.pharmacyRepository = new PharmacyDaoPostgres();
        this.medicationRepository = new MedicationDaoPostgres();
    }

    @Bean
    public FindPharmacy findPharmacy(){
        return new FindPharmacy(pharmacyRepository);
    }

    @Bean
    public CreatePharmacy createPharmacy(){
        return new CreatePharmacy(pharmacyRepository, medicationRepository);
    }
    @Bean
    public DeletePharmacy deletePharmacy(){
        return new DeletePharmacy(pharmacyRepository);
    }
    @Bean
    public UpdatePharmacy updatePharmacy(){
        return new UpdatePharmacy(pharmacyRepository);
    }
}
