package com.example.jorgi.figurasaleatorias.clases;

import java.io.Serializable;

/**
 * Created by Jorgi on 10/02/2016.
 */
public class Circulo extends Figura implements Serializable{

    protected float radio;

    public Circulo(){
        super("circulo");
    }

    public Circulo(int color, float radio) {
        super(color, "circulo");
        this.radio = radio;
    }
    public Circulo(String tipo, int imagen) {
        super(tipo, imagen);
    }

    public float getRadio() {
        return this.radio;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }

    public double area() {
        return Math.PI * radio * radio;
    }
}
