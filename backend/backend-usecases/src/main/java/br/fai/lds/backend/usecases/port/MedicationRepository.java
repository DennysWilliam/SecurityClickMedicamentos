package br.fai.lds.backend.usecases.port;

import br.fai.lds.domain.MedicationModel;

import java.util.List;
import java.util.Map;

public interface MedicationRepository {
    MedicationModel findById(final int id);

    List<MedicationModel> findAll();
    List<MedicationModel> findAll(final int id);

    List<MedicationModel> findByCriteria(Map<String, String> criteria);

    boolean update(final MedicationModel medicationModel);
    void updatePharmacies();

    boolean deleteById(final int id);

    int create(final MedicationModel medicationModel);

}
