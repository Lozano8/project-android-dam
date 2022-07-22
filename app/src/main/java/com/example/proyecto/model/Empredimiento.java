package com.example.proyecto.model;

public class Empredimiento {
    String nombre, direccion, contacto, descripcion;

    public Empredimiento(){}

    public Empredimiento(String nombre, String direccion, String contacto, String descripcion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.descripcion=descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
