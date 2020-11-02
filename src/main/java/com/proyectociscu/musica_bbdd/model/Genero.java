package com.proyectociscu.musica_bbdd.model;

public class Genero {

    private int id;
    private String nombre;

    public Genero() {
        this(-1, "");
    }

    public Genero(String nombre) {
        this.nombre = nombre;
    }

    public Genero(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Genero{" + "id=" + id + ", nombre=" + nombre + '}';
    }

}
