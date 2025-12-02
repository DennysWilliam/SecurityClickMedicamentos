package br.fai.lds.backend.usecases.pharmacy;

import br.fai.lds.backend.usecases.port.PharmacyRepository;
import br.fai.lds.backend.usecases.port.MedicationRepository;
import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.domain.MedicationModel;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class CreatePharmacy {

    private final PharmacyRepository pharmacyRepository;
    private final MedicationRepository medicationRepository;

    public CreatePharmacy(PharmacyRepository pharmacyRepository, MedicationRepository medicationRepository) {
        this.pharmacyRepository = pharmacyRepository;
        this.medicationRepository = medicationRepository;
    }

    public String createPharmacy(final PharmacyModel farmacia) {
        if (farmacia == null) {
            return "O campo farmácia está vazio";
        }

        String municipioNorm = removeAccentsAfterJava7(farmacia.getMunicipio().toLowerCase());
        farmacia.setMunicipio(municipioNorm);

        if (farmacia.getNomeFantasia().isEmpty()
                || farmacia.getCnpj().isEmpty()
                || farmacia.getCep().isEmpty()
                || farmacia.getMunicipio().isEmpty()
                || farmacia.getUf().isEmpty()
                || farmacia.getEndereco().isEmpty()
                || farmacia.getEmail().isEmpty()
                || farmacia.getTelefone().isEmpty()
                || farmacia.isSituacao()
                || farmacia.getHorarioFuncionamento().isEmpty()) {
            return "Algum campo obrigatório está vazio";
        }

        int id = 0;
        try {
            id = pharmacyRepository.create(farmacia);
            farmacia.setId(id);
        } catch (Exception e) {
            return "Exceção ao criar farmácia: " + e;
        }

        return "Farmácia criada com ID: " + id;
    }

    public static String removeAccentsAfterJava7(String value) {
        String normalizer = Normalizer.normalize(value, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalizer).replaceAll("");
    }
}
