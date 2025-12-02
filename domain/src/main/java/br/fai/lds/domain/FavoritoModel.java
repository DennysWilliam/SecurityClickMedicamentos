package br.fai.lds.domain;

import java.time.LocalDateTime;

public class FavoritoModel {
    private Long id;
    private Long idUsuario;
    private Long idMedicamento;
    private LocalDateTime dataCadastro;

    private String medicamentoNome;
    private String principioAtivo;
    private String farmaciaNome;
    private Boolean disponivel;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Long idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getMedicamentoNome() {
        return medicamentoNome;
    }

    public void setMedicamentoNome(String medicamentoNome) {
        this.medicamentoNome = medicamentoNome;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public String getFarmaciaNome() {
        return farmaciaNome;
    }

    public void setFarmaciaNome(String farmaciaNome) {
        this.farmaciaNome = farmaciaNome;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
