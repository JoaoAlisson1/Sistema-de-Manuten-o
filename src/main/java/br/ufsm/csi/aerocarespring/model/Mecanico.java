package br.ufsm.csi.aerocarespring.model;

public class Mecanico {

    private Integer id;
    private String nome;
    private String registroAnac;
    private String especialidade;
    private boolean ativo;

    public Mecanico(Integer id, String nome, String registroAnac, String especialidade, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.registroAnac = registroAnac;
        this.especialidade = especialidade;
        this.ativo = ativo;
    }

    public Mecanico() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistroAnac() {
        return registroAnac;
    }

    public void setRegistroAnac(String registroAnac) {
        this.registroAnac = registroAnac;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
