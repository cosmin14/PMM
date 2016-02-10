package com.example.jorgi.ejercicionavidadBBDDmaps.clases;

/**
 * Created by Jorgi on 16/01/2016.
 */
public class Pedido {

    private int codigo;
    private String usuarioDNI;
    private String zonaId;
    private String peso;
    private String tarifaPeso;
    private float express;

    public Pedido( String usuarioDNI, String zonaId, String peso, String tarifaPeso, float express) {
        this.usuarioDNI = usuarioDNI;
        this.zonaId = zonaId;
        this.peso = peso;
        this.tarifaPeso = tarifaPeso;
        this.express = express;
    }

    public Pedido() {
    }

    /* GETTERS */


    public int getCodigo() {
        return codigo;
    }

    public String getUsuarioDNI() {
        return usuarioDNI;
    }

    public String getZonaId() {
        return zonaId;
    }

    public String getPeso() {
        return peso;
    }

    public String getTarifaPeso() {
        return tarifaPeso;
    }




                                        /* SETTERS */


    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setUsuarioDNI(String usuarioDNI) {
        this.usuarioDNI = usuarioDNI;
    }

    public void setZonaId(String zonaId) {
        this.zonaId = zonaId;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public void setTarifaPeso(String tarifaPeso) {
        this.tarifaPeso = tarifaPeso;
    }

    public float getExpress() {
        return express;
    }

    public void setExpress(float express) {
        this.express = express;
    }
}
