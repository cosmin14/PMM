package com.example.jorgi.figurasaleatorias.clases;

/**
 * Created by Jorgi on 10/02/2016.
 */
public class Cuadrado extends Figuras {

    int lado;

    public Cuadrado(String nombre, int imagen) {
        super(nombre, imagen);
    }

    public Cuadrado(String nombre, int imagen, int lado) {
        super(nombre, imagen);
        this.lado = lado;
    }
}
