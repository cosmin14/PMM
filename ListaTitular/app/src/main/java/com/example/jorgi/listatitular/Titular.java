package com.example.jorgi.listatitular;

/**
 * Created by Jorgi on 02/11/2015.
 */
public class Titular {
    private String titulo;
    private String subtitulo;

    public Titular(String titulo, String subtitulo) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }
}
