package com.proyectomusica.musica_bbdd.model;

public class Lista {

    protected int id;
    protected String nombre;
    protected String descripcion;
    protected int id_ususario;

    public Lista() {}

    public Lista(int id, String nombre, String descripcion, int id_ususario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_ususario = id_ususario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_ususario() {
        return id_ususario;
    }

    public void setId_ususario(int id_ususario) {
        this.id_ususario = id_ususario;
    }

    @Override
    public String toString() {
        return "Lista{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", id_ususario=" + id_ususario + '}';
    }

}
