package br.fai.lds.backendspringlds.restcontrollers;

import br.fai.lds.backendspringlds.configuration.PharmacyBackendConfiguration;
import br.fai.lds.domain.PharmacyModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacy")
@CrossOrigin(origins = "http://localhost:4200")
public class PharmacyRestController {
    private final PharmacyBackendConfiguration pharmacyBackendConfiguration = new PharmacyBackendConfiguration();

    @GetMapping("/all")
    public List<PharmacyModel> getPharmacys(){
        System.out.println("chegou aqui farmacia");
        List<PharmacyModel> pharmacys = pharmacyBackendConfiguration.findPharmacy().find();
        return pharmacys;
    }

    @PutMapping("/update")
    public boolean getUpdatedPharmacy(@RequestBody PharmacyModel pharmacyModel){
        return pharmacyBackendConfiguration.updatePharmacy().update(pharmacyModel);
    }

    @PostMapping("/add")
    public String getCreatedPharmacy(@RequestBody PharmacyModel pharmacyModel){
        return pharmacyBackendConfiguration.createPharmacy().createPharmacy(pharmacyModel);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") final int id){
        return pharmacyBackendConfiguration.deletePharmacy().delete(id);
    }
}
