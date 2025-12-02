package br.fai.lds.frontendspringlds.configuration;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.frontend.usecases.pharmacy.*;
import br.fai.lds.frontend.usecases.port.RestService;
import br.fai.lds.frontendspringlds.controller.impl.RestApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PharmacyFrontendAppConfiguration {
    @Bean
    public ShowAllPharmacys showAllPharmacys(){
        RestService<PharmacyModel> restService = new RestApiController<>();
        return new ShowAllPharmacys(restService);
    }

    @Bean
    public FindPharmacyById findPharmacyById(){
        RestService<PharmacyModel> restService = new RestApiController<>();
        return new FindPharmacyById(restService);
    }

    @Bean
    public UpdatePharmacy updatePharmacy(){
        RestService<PharmacyModel> restService = new RestApiController<>();
        return new UpdatePharmacy(restService);
    }

    @Bean
    public CreatePharmacy createPharmacy(){
        RestService<PharmacyModel> restService = new RestApiController<>();
        return new CreatePharmacy(restService);
    }

    @Bean
    public DeletePharmacy deletePharmacy(){
        RestService<PharmacyModel> restService = new RestApiController<>();
        return new DeletePharmacy(restService);
    }

}
