package com.example.jorgi.figurasaleatorias.clases;

/**
 * Created by Jorgi on 10/02/2016.
 */
public class Cuadrado extends Figura {

    protected float base;
    protected float altura;

    public Cuadrado(){
        super("cuadrado");
    }

    public Cuadrado(int color, float base) {
        super(color, "cuadrado");
        this.base = base;
    }

    public Cuadrado(String tipo, int imagen) {
        super(tipo, imagen);
    }

    public double getLado() {
        return base;
    }

    public void setLado(float lado) {
        base = altura = lado;
    }

    public void setBase(float base) {
        this.base = this.altura = base;
    }

    public void setAltura(float altura) {
        this.base = this.altura = altura;
    }

    public double area(){
        return base * altura;
    }
}
