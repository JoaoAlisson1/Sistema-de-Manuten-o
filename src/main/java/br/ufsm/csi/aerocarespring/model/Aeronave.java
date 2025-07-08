package br.ufsm.csi.aerocarespring.model;

public class Aeronave {

    private Integer id;
    private String modelo;
    private String fabricante;
    private int anoFabricacao;
    private String registroNacional;

    public Aeronave(Integer id, String modelo, String fabricante, int anoFabricacao, String registroNacional) {
        this.id = id;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.anoFabricacao = anoFabricacao;
        this.registroNacional = registroNacional;
    }

    public Aeronave() {
        // Construtor padrão vazio
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getRegistroNacional() {
        return registroNacional;
    }

    public void setRegistroNacional(String registroNacional) {
        this.registroNacional = registroNacional;
    }
}
