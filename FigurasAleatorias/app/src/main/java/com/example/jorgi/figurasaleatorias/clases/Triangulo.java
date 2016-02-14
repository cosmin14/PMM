package com.example.jorgi.figurasaleatorias.clases;

/**
 * Created by Jorgi on 10/02/2016.
 */
public class Triangulo extends Figura {

    protected float base;
    protected float altura;

    public Triangulo(){
        super("triangulo");
    }

    public Triangulo(int color, float base, float altura) {
        super(color, "triangulo");
        this.base = base;
        this.altura = altura;
    }

    public Triangulo(String tipo, int imagen) {
        super(tipo, imagen);
    }

    public float getBase() {
        return base;
    }

    public float getAltura() {
        return altura;
    }

    public void setBase(float base) {
        this.base = base;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float area(){
        return (base * altura) / 2;
    }
}
