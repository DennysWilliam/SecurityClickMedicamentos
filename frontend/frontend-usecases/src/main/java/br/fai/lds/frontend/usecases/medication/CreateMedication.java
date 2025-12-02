package br.fai.lds.frontend.usecases.medication;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.domain.MedicationModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class CreateMedication {
    private final RestService<MedicationModel> restService;

    public CreateMedication(RestService<MedicationModel> restService) {
        this.restService = restService;
    }
    public int createPharmacy(final MedicationModel medicationModel){
        final String resource = "/pharmacy/add";
        return restService.post(resource, medicationModel);
    }
}
