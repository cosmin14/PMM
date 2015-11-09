package com.example.jorgi.listatitular;

/**
 * Created by Jorgi on 02/11/2015.
 */
public class Titular {
    private String titulo;
    private String subtitulo;
    private int edad;

    public Titular(String titulo, String subtitulo, int edad) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.edad = edad;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public int getEdad() {
        return edad;
    }
}
