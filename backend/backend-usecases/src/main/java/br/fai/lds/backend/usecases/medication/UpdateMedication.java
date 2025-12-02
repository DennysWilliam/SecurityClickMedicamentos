package br.fai.lds.backend.usecases.medication;

import br.fai.lds.backend.usecases.port.*;
import br.fai.lds.domain.UserModel;
import br.fai.lds.domain.MedicationModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateMedication {

    private final MedicationRepository medicationRepository;
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final PharmacyRepository pharmacyRepository;

    public UpdateMedication(MedicationRepository medicationRepository,
                            UserRepository userRepository,
                            LoginRepository loginRepository,
                            PharmacyRepository pharmacyRepository) {
        this.medicationRepository = medicationRepository;
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.pharmacyRepository = pharmacyRepository;
    }

    public boolean update(final int id, final MedicationModel medicationModel){

        final MedicationModel existingMedication = medicationRepository.findById(id);
        if (existingMedication == null) {
            return false;
        }

        existingMedication.setNome(medicationModel.getNome());
        existingMedication.setPrincipioAtivo(medicationModel.getPrincipioAtivo());
        existingMedication.setDataValidade(medicationModel.getDataValidade());

        boolean updatedMedication = medicationRepository.update(existingMedication);

        if(updatedMedication){

            final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            final Date serverDate = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(serverDate);
            final String date = dateFormat.format(calendar.getTime());

        }

        return updatedMedication;
    }
}
