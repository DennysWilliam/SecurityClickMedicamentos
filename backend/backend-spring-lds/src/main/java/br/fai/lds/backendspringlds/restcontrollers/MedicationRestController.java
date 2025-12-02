package br.fai.lds.backendspringlds.restcontrollers;

import br.fai.lds.backendspringlds.configuration.MedicationBackendConfiguration;
import br.fai.lds.domain.MedicationModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medication")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicationRestController {

    private final MedicationBackendConfiguration medicationBackendConfiguration = new MedicationBackendConfiguration();

    @GetMapping("/all")
    public List<MedicationModel> getMedications() {
        return medicationBackendConfiguration.findMedication().find();
    }

    @GetMapping("/{id}")
    public MedicationModel getMedicationById(@PathVariable("id") int id) {
        return medicationBackendConfiguration.findMedication().findById(id);
    }

    @PostMapping("/create")
    public MedicationModel createMedication(@RequestBody MedicationModel medication) {

        if (medication.getData_cadastro() == null) {
            medication.setData_cadastro(new java.sql.Timestamp(System.currentTimeMillis()));
        }
        return medication;
    }




}
