package com.example.jorgi.ejercicionavidadBBDD;

/**
 * Created by Jorgi on 16/01/2016.
 */
public class Usuario {

    private int codigo;
    private String nombre;
    private String dni;
    private String apellido1;
    private String apellido2;
    private String comAut;
    private String provincia;
    private String localidad;
    private String direccion;
    private String email;

    public Usuario(String dni, String email, String direccion, String provincia, String localidad, String apellido1, String apellido2, String comAut, String nombre) {
        this.email = email;
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.comAut = comAut;
        this.nombre = nombre;
        this.dni = dni;
    }

    public Usuario() {
    }

    /* GETTERS */


    public int getCodigo() {
        return codigo;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getComAut() {
        return comAut;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }


                                            /* SETTERS*/

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDni(String nombre) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setComAut(String comAut) {
        this.comAut = comAut;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
