package br.fai.lds.frontend.usecases.pharmacy;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class CreatePharmacy {
    private final RestService<PharmacyModel> restService;

    public CreatePharmacy(RestService<PharmacyModel> restService) {
        this.restService = restService;
    }
    public int createPharmacy(final PharmacyModel pharmacyModel){
        final String resource = "/pharmacy/add";
        return restService.post(resource, pharmacyModel);
    }
}
