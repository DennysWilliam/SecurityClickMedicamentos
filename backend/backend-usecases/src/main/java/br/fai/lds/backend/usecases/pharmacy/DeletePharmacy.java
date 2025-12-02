package br.fai.lds.backend.usecases.pharmacy;

import br.fai.lds.backend.usecases.port.PharmacyRepository;

public class DeletePharmacy {
    private final PharmacyRepository pharmacyRepository;

    public DeletePharmacy(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public boolean delete(final int id){
        if(id <= 0){
            return false;
        }
        boolean response = pharmacyRepository.deleteById(id);
        return response;
    }
}
