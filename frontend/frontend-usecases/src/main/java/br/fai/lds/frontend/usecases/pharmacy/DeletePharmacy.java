package br.fai.lds.frontend.usecases.pharmacy;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.domain.UserModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class DeletePharmacy {
    private final RestService<PharmacyModel> restService;

    public DeletePharmacy(RestService<PharmacyModel> restService) {
        this.restService = restService;
    }

    public boolean deletePharmacy(final int id){
        if(id <= 0){
            return false;
        }
        final String resource = "/pharmacy/delete/" + id;
        return restService.delete(resource);
    }
}
