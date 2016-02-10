package com.example.jorgi.figurasaleatorias.clases;

/**
 * Created by Jorgi on 10/02/2016.
 */
public class Triangulo extends Figuras {

    int base;
    int altura;

    public Triangulo(String nombre, int imagen) {
        super(nombre, imagen);
    }

    public Triangulo(String nombre, int imagen, int base, int altura) {
        super(nombre, imagen);
        this.base = base;
        this.altura = altura;
    }
}
