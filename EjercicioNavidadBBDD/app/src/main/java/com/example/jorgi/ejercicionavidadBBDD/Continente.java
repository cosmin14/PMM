package com.example.jorgi.ejercicionavidadBBDD;

import java.io.Serializable;

/**
 * Created by Jorgi on 07/01/2016.
 */
public class Continente implements Serializable {

    private int id;
    private String nombre;
    private int zona;
    private int precio;
    private int imagen;

    public Continente(int id, String nombre, int zona, int precio, int imagen) {
        this.id = id;
        this.nombre = nombre;
        this.zona = zona;
        this.precio = precio;
        this.imagen = imagen;
    }

            /** GETTERS */

    public int getId() {

        return id;
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

    public int getImagen() {
        return imagen;
    }

    /** SETTERS */

    public void setId(int id) {
        this.id = id;
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

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
