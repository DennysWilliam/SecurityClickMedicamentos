package br.fai.lds.backend.usecases.medication;

import br.fai.lds.backend.usecases.exception.InvalidIdException;
import br.fai.lds.backend.usecases.exception.NotFoundException;
import br.fai.lds.backend.usecases.port.MedicationRepository;
import br.fai.lds.domain.PharmacyModel;
import br.fai.lds.domain.MedicationModel;

import java.util.ArrayList;
import java.util.List;

public class FindMedication {
    private final MedicationRepository medicationRepository;

    public FindMedication(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<MedicationModel> find(){

        final List<MedicationModel> pharmacies = medicationRepository.findAll();
        if(pharmacies == null){
            return null;
        }
        return pharmacies;
    }
    public List<MedicationModel> find(final int id){
        if(id < 0){
            throw new InvalidIdException();
        }
        medicationRepository.updatePharmacies();
        final List<MedicationModel> pharmacies = medicationRepository.findAll(id);
        if(pharmacies == null){
            final String message = "O id " + id + " nao foi encontrado";
            throw new NotFoundException(message);
        }

        return pharmacies;
    }

    public MedicationModel findById(final int id){
        MedicationModel medicationById = medicationRepository.findById(id);
        if (medicationById == null) return null;
        return medicationById;
    }

    public MedicationModel create(MedicationModel medication) {
        if (medication.getNome() == null || medication.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do medicamento é obrigatório.");
        }
        if (medication.getPrincipioAtivo() == null || medication.getPrincipioAtivo().isEmpty()) {
            throw new IllegalArgumentException("O princípio ativo é obrigatório.");
        }

        if (medication.getData_cadastro() == null) {
            medication.setData_cadastro(new java.sql.Timestamp(System.currentTimeMillis()));
        }

        int idCriado = medicationRepository.create(medication);

        medication.setId((long) idCriado);

        return medication;
    }

}
