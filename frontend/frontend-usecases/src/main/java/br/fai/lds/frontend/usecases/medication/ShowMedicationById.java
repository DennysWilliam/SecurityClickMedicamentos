package br.fai.lds.frontend.usecases.medication;

import br.fai.lds.domain.MedicationModel;
import br.fai.lds.frontend.usecases.port.RestService;

import java.util.List;

public class ShowMedicationById {

    private final RestService<MedicationModel> restService;

    public ShowMedicationById(RestService<MedicationModel> restService) {
        this.restService = restService;
    }

    public MedicationModel showMedicationById(final int id){
        final String resource = "/medication/find/" + id;
        final MedicationModel medicationModel = restService.getById(resource, MedicationModel.class);
        return medicationModel;
    }
}
