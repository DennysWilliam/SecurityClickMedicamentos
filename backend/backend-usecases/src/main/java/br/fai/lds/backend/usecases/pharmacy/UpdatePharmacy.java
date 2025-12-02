package br.fai.lds.backend.usecases.pharmacy;

import br.fai.lds.backend.usecases.port.PharmacyRepository;
import br.fai.lds.domain.PharmacyModel;

public class UpdatePharmacy {

    private final PharmacyRepository pharmacyRepository;

    public UpdatePharmacy(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public boolean update(final PharmacyModel pharmacyModel){

        final PharmacyModel bYId = pharmacyRepository.findById(pharmacyModel.getId());
        bYId.setNomeFantasia(pharmacyModel.getNomeFantasia());
        bYId.setCnpj(pharmacyModel.getCnpj());
        bYId.setCep(pharmacyModel.getCep());
        bYId.setMunicipio(pharmacyModel.getMunicipio().toLowerCase());
        bYId.setUf(pharmacyModel.getUf());
        bYId.setEndereco(pharmacyModel.getEndereco());
        bYId.setDataCadastro(pharmacyModel.getDataCadastro());
        bYId.setHorarioFuncionamento(pharmacyModel.getHorarioFuncionamento());
        bYId.setSituacao(pharmacyModel.isSituacao());
        bYId.setTelefone(pharmacyModel.getTelefone());
        bYId.setEmail(pharmacyModel.getEmail());

        boolean updatedPharmacy = false;
        try {
            updatedPharmacy = pharmacyRepository.update(bYId);
            return updatedPharmacy;
        } catch (Exception e) {
            return true;
        }

    }
}
