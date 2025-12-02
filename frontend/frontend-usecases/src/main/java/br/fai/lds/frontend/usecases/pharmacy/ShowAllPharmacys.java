package br.fai.lds.frontend.usecases.pharmacy;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.frontend.usecases.port.RestService;

import java.util.List;

public class ShowAllPharmacys {
    private final RestService<PharmacyModel> restService;

    public ShowAllPharmacys(RestService<PharmacyModel> restService) {
        this.restService = restService;
    }

    public List<PharmacyModel> showAllPharmacys(){
        final String resource = "/pharmacy/all";
        final List<PharmacyModel> pharmacyModels = restService.get(resource);
        return pharmacyModels;
    }
    public List<PharmacyModel> showAllPharmacysByCity(final PharmacyModel pharmacyModel){
        final String resource = "/pharmacy/allbycity";
        final List<PharmacyModel> pharmacyModels = restService.get(resource, pharmacyModel);
        return pharmacyModels;
    }


    public List<PharmacyModel> showAllPharmacysById(final int id){
        final String resource = "/pharmacy/allbyid/" + id;
        final List<PharmacyModel> pharmacyModels = restService.get(resource);
        return pharmacyModels;
    }
}
