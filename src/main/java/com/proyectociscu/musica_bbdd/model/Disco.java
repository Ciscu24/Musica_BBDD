/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectociscu.model;

import java.sql.Timestamp;

/**
 *
 * @author matad
 */
public class Disco {

    private int id;
    private String nombre;
    private String foto;
    private int id_artista;
    private Timestamp fecha_produccion;

    public Disco() {
    }

    public Disco(int id, String nombre, String foto, int id_artista, Timestamp fecha_produccion) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.id_artista = id_artista;
        this.fecha_produccion = fecha_produccion;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getId_artista() {
        return id_artista;
    }

    public void setId_artista(int id_artista) {
        this.id_artista = id_artista;
    }

    public Timestamp getFecha_produccion() {
        return fecha_produccion;
    }

    public void setFecha_produccion(Timestamp fecha_produccion) {
        this.fecha_produccion = fecha_produccion;
    }

    @Override
    public String toString() {
        return "disco{" + "id=" + id + ", nombre=" + nombre + ", foto=" + foto + ", id_artista=" + id_artista + ", fecha_produccion=" + fecha_produccion + '}';
    }

}
