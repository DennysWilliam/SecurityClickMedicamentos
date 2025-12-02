package br.fai.lds.domain;

public class EstoqueModel {

    private Long id;
    private Long farmaciaId;
    private Long medicamentoId;
    private Integer quantidade;

    public EstoqueModel() {}

    public EstoqueModel(Long id, Long farmaciaId, Long medicamentoId, Integer quantidade) {
        this.id = id;
        this.farmaciaId = farmaciaId;
        this.medicamentoId = medicamentoId;
        this.quantidade = quantidade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getFarmaciaId() { return farmaciaId; }
    public void setFarmaciaId(Long farmaciaId) { this.farmaciaId = farmaciaId; }

    public Long getMedicamentoId() { return medicamentoId; }
    public void setMedicamentoId(Long medicamentoId) { this.medicamentoId = medicamentoId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }
}
