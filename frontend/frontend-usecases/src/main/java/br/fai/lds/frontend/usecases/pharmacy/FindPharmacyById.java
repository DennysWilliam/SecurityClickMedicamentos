package br.fai.lds.frontend.usecases.pharmacy;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class FindPharmacyById {
    private final RestService<PharmacyModel> restService;

    public FindPharmacyById(final RestService<PharmacyModel> restService) {
        this.restService = restService;
    }

    public PharmacyModel findById(final int id){
        final String resource = "/pharmacy/find/" + id;
        PharmacyModel byId = restService.getById(resource, PharmacyModel.class);
        return byId;
    }
}
