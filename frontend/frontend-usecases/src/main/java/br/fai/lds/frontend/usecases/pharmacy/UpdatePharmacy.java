package br.fai.lds.frontend.usecases.pharmacy;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class UpdatePharmacy {
    private final RestService<PharmacyModel> restService;

    public UpdatePharmacy(final RestService<PharmacyModel> restService) {
        this.restService = restService;
    }

    public boolean updatePharmacy(final PharmacyModel pharmacyModel){
        final String resource = "/pharmacy/update";
        boolean pharmacy = restService.put(resource, pharmacyModel);
        return pharmacy;
    }
}
