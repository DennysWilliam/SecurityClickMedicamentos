package br.fai.lds.domain;

import java.sql.Timestamp;

public class MedicationModel {

    private Long id;
    private String nome;
    private String principioAtivo;
    private String dataValidade;
    private int farmaciaId;
    private String farmaciaNome;
    private String descricao;
    private String categoria;
    private Timestamp data_cadastro;
    private Integer quantidadeEstoque;
    private Boolean disponivel;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Timestamp getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Timestamp data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrincipioAtivo() {
        return principioAtivo;
    }

    public void setPrincipioAtivo(String principioAtivo) {
        this.principioAtivo = principioAtivo;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public int getFarmaciaId() {
        return farmaciaId;
    }

    public void setFarmaciaId(int farmaciaId) {
        this.farmaciaId = farmaciaId;
    }

    public String getFarmaciaNome() {
        return farmaciaNome;
    }

    public void setFarmaciaNome(String farmaciaNome) {
        this.farmaciaNome = farmaciaNome;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }
}
