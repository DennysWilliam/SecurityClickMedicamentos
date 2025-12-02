package br.fai.lds.frontend.usecases.medication;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.domain.MedicationModel;
import br.fai.lds.frontend.usecases.port.RestService;

public class UpdateMedication {
    private final RestService<MedicationModel> restService;

    public UpdateMedication(final RestService<MedicationModel> restService) {
        this.restService = restService;
    }

    public boolean updatePharmacy(final int id, final MedicationModel medicationModel){
        final String resource = "/medication/update/" + id;
        boolean medication = restService.put(resource, medicationModel);
        return medication;
    }
}
