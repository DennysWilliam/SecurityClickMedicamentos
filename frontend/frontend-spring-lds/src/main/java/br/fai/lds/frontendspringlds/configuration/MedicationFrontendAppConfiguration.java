package br.fai.lds.frontendspringlds.configuration;

import br.fai.lds.domain.MedicationModel;
import br.fai.lds.frontend.usecases.port.RestService;
import br.fai.lds.frontend.usecases.medication.ShowAllMedication;
import br.fai.lds.frontend.usecases.medication.ShowMedicationById;
import br.fai.lds.frontend.usecases.medication.UpdateMedication;
import br.fai.lds.frontendspringlds.controller.impl.RestApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicationFrontendAppConfiguration {
    @Bean
    public ShowAllMedication showAllVacancies(){
        RestService<MedicationModel> restService = new RestApiController<>();
        return new ShowAllMedication(restService);
    }
    @Bean
    public UpdateMedication updateMedication(){
        RestService<MedicationModel> restService = new RestApiController<>();
        return new UpdateMedication(restService);
     }

     @Bean
    public ShowMedicationById showMedicationById(){
        RestService<MedicationModel> restService = new RestApiController<>();
        return new ShowMedicationById(restService);
     }
}
