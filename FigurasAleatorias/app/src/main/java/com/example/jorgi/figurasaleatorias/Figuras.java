package com.example.jorgi.figurasaleatorias;

/**
 * Created by Jorgi on 14/12/2015.
 */
public class Figuras {

    private String nombre;
    private int imagen;

    public Figuras(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }
}
