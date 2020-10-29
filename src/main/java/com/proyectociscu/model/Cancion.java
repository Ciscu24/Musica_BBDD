/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectociscu.model;

/**
 *
 * @author matad
 */
public class Cancion {

    private int id;
    private String nombre;
    private int duracion;
    private int id_genero;
    private int id_disco;

    public Cancion() {
    }

    public Cancion(int id, String nombre, int duracion, int id_genero, int id_disco) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.id_genero = id_genero;
        this.id_disco = id_disco;
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }

    public int getId_disco() {
        return id_disco;
    }

    public void setId_disco(int id_disco) {
        this.id_disco = id_disco;
    }

    @Override
    public String toString() {
        return "cancion{" + "id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", id_genero=" + id_genero + ", id_disco=" + id_disco + '}';
    }

}
