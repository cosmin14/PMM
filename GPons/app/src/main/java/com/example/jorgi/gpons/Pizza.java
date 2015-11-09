package com.example.jorgi.gpons;

/**
 * Created by Jorgi on 09/11/2015.
 */
public class Pizza {

    private String nombre, ingredientes;
    private int imagen;
    private double precio;

    public Pizza(String nombre, String ingredientes, double precio, int imagen) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public int getImagen() {
        return imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
