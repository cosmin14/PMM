package com.example.jorgi.figurasaleatorias.clases;

/**
 * Created by Jorgi on 10/02/2016.
 */
public class Circulo extends Figuras {

    int radio;

    public Circulo(String nombre, int imagen) {
        super(nombre, imagen);
    }

    public Circulo(String nombre, int imagen, int radio) {
        super(nombre, imagen);
        this.radio = radio;
    }
}
