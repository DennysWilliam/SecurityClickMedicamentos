package br.fai.lds.backend.usecases.port;

import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.domain.PharmacyModel;

import java.util.List;
import java.util.Map;

public interface PharmacyRepository {
    PharmacyModel findById(final int id);

    List<PharmacyModel> findAll();
    List<PharmacyModel> findAllById(final int id);
    List<PharmacyModel> findAllByCity(final PharmacyModel pharmacyModel);

    List<PharmacyModel> findByCriteria(Map<String, String> criteria);

    boolean update(final PharmacyModel pharmacyModel);

    boolean deleteById(final int id);

    int create(final PharmacyModel pharmacyModel);


}
