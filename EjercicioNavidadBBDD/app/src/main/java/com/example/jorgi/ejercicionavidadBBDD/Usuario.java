package com.example.jorgi.ejercicionavidadBBDD;

import java.io.Serializable;

/**
 * Created by Jorgi on 16/01/2016.
 */
public class Usuario implements Serializable{

    private String codigo;
    private String nombre;
    private String dni;
    private String apellido1;
    private String apellido2;
    private String comAut;
    private String provincia;
    private String localidad;
    private String direccion;
    private String email;
    private String password;

    public Usuario(String codigo, String email, String nombre, String password) {
        this.codigo = codigo;
        this.email = email;
        this.nombre = nombre;
        this.password = password;
    }

    public Usuario(String dni, String email, String direccion, String provincia, String localidad, String apellido1, String apellido2, String comAut, String nombre, String password) {
        this.email = email;
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.comAut = comAut;
        this.nombre = nombre;
        this.dni = dni;
        this.password = password;
    }
    public Usuario(String codigo, String dni, String email, String direccion, String provincia, String localidad, String apellido1, String apellido2, String comAut, String nombre, String password) {
        this.codigo = codigo;
        this.email = email;
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.comAut = comAut;
        this.nombre = nombre;
        this.dni = dni;
        this.password = password;
    }

    public Usuario() {
    }

    /* GETTERS */


    public String getCodigo() {
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

    public String getPassword() {
        return password;
    }


                                            /* SETTERS*/

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDni(String dni) {
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

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean validateLogin(String email, String password, String emailValidate, String passwordValidate){
        if (email.equalsIgnoreCase(emailValidate) && password.equals(passwordValidate)){
            return true;
        }else{
            return false;
        }
    }


}
