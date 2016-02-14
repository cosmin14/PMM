package com.example.jorgi.figurasaleatorias.clases;

/**
 * Created by Jorgi on 10/02/2016.
 */
public class Rectangulo extends Figura {

    protected float base;
    protected float altura;

    public Rectangulo(){
        super("rectangulo");
    }

    public Rectangulo(int color, float base, float altura) {
        super(color, "rectangulo");
        this.base = base;
        this.altura = altura;
    }

    public Rectangulo(String tipo, int imagen) {
        super(tipo, imagen);
    }

    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }

    public void setBase(float base) {
        this.base = base;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public double area(){
        return base * altura;
    }
}
