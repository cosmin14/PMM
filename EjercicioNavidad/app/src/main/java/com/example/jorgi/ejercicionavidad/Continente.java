package com.example.jorgi.ejercicionavidad;

/**
 * Created by Jorgi on 30/12/2015.
 */
public class Continente {

    private String nombre;
    private int zona;
    private int precio;

    public Continente(String nombre, int zona, int precio) {
        this.nombre = nombre;
        this.zona = zona;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getZona() {
        return zona;
    }

    public int getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
