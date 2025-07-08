package br.ufsm.csi.aerocarespring.model;

import java.time.LocalDate;

public class OrdemDeServico {

    private Integer id;
    private String descricaoServico;
    private String tipoManutencao;
    private LocalDate dataSolicitacao;
    private LocalDate dataConclusao;
    private String status;
    private Aeronave aeronave;
    private Mecanico mecanico;

    public OrdemDeServico(Integer id, String descricaoServico, LocalDate dataSolicitacao,
                          LocalDate dataConclusao, String status,
                          Aeronave aeronave, Mecanico mecanico) {
        this.id = id;
        this.descricaoServico = descricaoServico;
        this.dataSolicitacao = dataSolicitacao;
        this.dataConclusao = dataConclusao;
        this.status = status;
        this.aeronave = aeronave;
        this.mecanico = mecanico;
    }

    public OrdemDeServico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public String getTipoManutencao() {
        return tipoManutencao;
    }

    public void setTipoManutencao(String tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Aeronave getAeronave() {
        return aeronave;
    }

    public void setAeronave(Aeronave aeronave) {
        this.aeronave = aeronave;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }
}
