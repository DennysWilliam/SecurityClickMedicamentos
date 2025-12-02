package br.fai.lds.frontend.usecases.medication;

import br.fai.lds.domain.MedicationModel;
import br.fai.lds.frontend.usecases.port.RestService;

import java.util.List;

public class ShowAllMedication {
    private final RestService<MedicationModel> restService;

    public ShowAllMedication(RestService<MedicationModel> restService) {
        this.restService = restService;
    }

    public List<MedicationModel> showAllVacancies(final int id){
        final String resource = "/medication/all/" + id;
        final List<MedicationModel> medicationModels = restService.get(resource);
        return medicationModels;
    }
}
