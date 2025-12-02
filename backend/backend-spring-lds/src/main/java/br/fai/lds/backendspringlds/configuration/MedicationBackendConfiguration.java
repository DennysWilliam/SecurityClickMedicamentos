package br.fai.lds.backendspringlds.configuration;

import br.fai.lds.backend.implementation.repository.*;
import br.fai.lds.backend.usecases.port.*;
import br.fai.lds.backend.usecases.medication.FindMedication;
import br.fai.lds.backend.usecases.medication.UpdateMedication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicationBackendConfiguration {
    private final MedicationRepository medicationRepository;

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final PharmacyRepository pharmacyRepository;

    public MedicationBackendConfiguration() {
        this.pharmacyRepository = new PharmacyDaoPostgres();
        this.userRepository = new UserDaoPostgres();
        this.loginRepository = new LoginDaoPostgres();
        this.medicationRepository = new MedicationDaoPostgres();
    }

    @Bean
    public FindMedication findMedication(){
        return new FindMedication(medicationRepository);
    }

    @Bean
    public UpdateMedication updateMedication(){
        return new UpdateMedication(medicationRepository, userRepository, loginRepository, pharmacyRepository);
    }

}
