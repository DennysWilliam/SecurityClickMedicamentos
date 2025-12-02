package br.fai.lds.backend.usecases.pharmacy;

import br.fai.lds.backend.usecases.exception.InvalidIdException;
import br.fai.lds.backend.usecases.exception.NotFoundException;
import br.fai.lds.backend.usecases.port.PharmacyRepository;
import br.fai.lds.domain.PharmacyModel;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

public class FindPharmacy {

    private final PharmacyRepository pharmacyRepository;

    public FindPharmacy(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public List<PharmacyModel> find() {
        List<PharmacyModel> farmacias = pharmacyRepository.findAll();
        return farmacias != null ? farmacias : null;
    }

    public List<PharmacyModel> findByCity(final PharmacyModel farmacia) {
        String municipioNorm = removeAccentsAfterJava7(farmacia.getMunicipio().toLowerCase());
        farmacia.setMunicipio(municipioNorm);

        List<PharmacyModel> farmacias = pharmacyRepository.findAllByCity(farmacia);
        return farmacias != null ? farmacias : null;
    }

    public List<PharmacyModel> findAllById(final int idUsuarioAdmin) {
        List<PharmacyModel> farmacias = pharmacyRepository.findAllById(idUsuarioAdmin);
        return farmacias != null ? farmacias : null;
    }

    public PharmacyModel find(final int id) {
        if (id < 0) {
            throw new InvalidIdException();
        }

        PharmacyModel farmacia = pharmacyRepository.findById(id);
        if (farmacia == null) {
            throw new NotFoundException("O id " + id + " não foi encontrado");
        }

        return farmacia;
    }

    public static String removeAccentsAfterJava7(String value) {
        String normalizer = Normalizer.normalize(value, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizer).replaceAll("");
    }
}
