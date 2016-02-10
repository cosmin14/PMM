package com.example.jorgi.ejercicionavidadBBDDmaps.clases;

import java.io.Serializable;

/**
 * Created by Jorgi on 07/01/2016.
 */
public class Destino implements Serializable {

    private int id;
    private String nombre;
    private String zona;
    private int precio;
    private int imagen;

    public Destino(int id, String nombre, String zona, int precio, int imagen) {
        this.id = id;
        this.nombre = nombre;
        this.zona = zona;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Destino(String nombre, String zona, int precio, int imagen) {
        this.nombre = nombre;
        this.zona = zona;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Destino() {
    }

    /** GETTERS */

    public int getId() {

        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getZona() {
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

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
