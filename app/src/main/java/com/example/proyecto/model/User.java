package com.example.proyecto.model;

public class User {
    private String Nombre, Apellido, Contrasenia, Correo, Telefono, type;

    public User(){}

    public User(String nombre, String apellido, String contrasenia, String correo, String telefono, String type) {
        Nombre = nombre;
        Apellido = apellido;
        Contrasenia = contrasenia;
        Correo = correo;
        Telefono = telefono;
        this.type = type;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        Contrasenia = contrasenia;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
