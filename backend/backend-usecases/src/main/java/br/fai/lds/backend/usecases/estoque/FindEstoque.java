package br.fai.lds.backend.usecases.estoque;

import br.fai.lds.backend.usecases.port.EstoqueRepository;
import br.fai.lds.backend.usecases.port.MedicationRepository;
import br.fai.lds.domain.EstoqueModel;
import br.fai.lds.domain.MedicationModel;

import java.util.List;

public class FindEstoque {

    private final EstoqueRepository repository;
    private final MedicationRepository medicationRepository;

    public FindEstoque(EstoqueRepository repository, MedicationRepository medicationRepository) {
        this.repository = repository;
        this.medicationRepository = medicationRepository;
    }

    public List<EstoqueModel> findAll() {
        return repository.findAll();
    }

    public EstoqueModel findById(Long id) {
        return repository.findById(id);
    }

    public List<EstoqueModel> findByFarmacia(Long farmaciaId) {
        return repository.findByFarmaciaId(farmaciaId);
    }

    public Integer getQuantidadePorMedicamento(Long medicamentoId) {
        Integer qtd = repository.getQuantidadePorMedicamento(medicamentoId);
        return qtd != null ? qtd : 0;
    }

    public List<EstoqueModel> findMedicamentosComEstoque(Long farmaciaId) {

        List<MedicationModel> medicamentos = medicationRepository.findAll();
        System.out.println("chegou aqui " + medicamentos);

        List<EstoqueModel> estoques = repository.findByFarmaciaId(farmaciaId);

        System.out.println("vamos ver agr" + estoques);

        for (MedicationModel med : medicamentos) {
            EstoqueModel estoque = estoques.stream()
                    .filter(e -> e.getMedicamentoId().equals(med.getId().longValue()))
                    .findFirst()
                    .orElse(null);

            if (estoque != null) {
                med.setQuantidadeEstoque(estoque.getQuantidade());
                med.setDisponivel(estoque.getQuantidade() > 0);
            } else {
                med.setQuantidadeEstoque(0);
                med.setDisponivel(false);
            }
        }
        System.out.println("final" + medicamentos);
        return estoques;
    }
}
