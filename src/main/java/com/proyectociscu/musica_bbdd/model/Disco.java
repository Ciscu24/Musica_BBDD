/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectociscu.musica_bbdd.model;

import java.sql.Timestamp;

/**
 *
 * @author matad
 */
public class Disco {

    protected int id;
    protected String nombre;
    protected String foto;
    protected Timestamp fecha_produccion;
    protected Artista[] artistas;
    boolean synced;

    public Disco(int id) {
        this.id = id;
        this.nombre = "";
        this.foto = "";
        this.fecha_produccion = fecha_produccion;
        this.artistas = artistas;
        this.synced=false;

       
    }

    public Disco() {
    }

    public Disco(int id, String nombre, String foto, Timestamp fecha_produccion, Artista[] artistas) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;     
        this.fecha_produccion = fecha_produccion;
        this.artistas = artistas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artista[] getArtistas() {
        return artistas;
    }

    public void setArtistas(Artista[] artistas) {
        this.artistas = artistas;
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

    public Timestamp getFecha_produccion() {
        return fecha_produccion;
    }

    public void setFecha_produccion(Timestamp fecha_produccion) {
        this.fecha_produccion = fecha_produccion;
    }

    @Override
    public String toString() {
        return "disco{" + "id=" + id + ", nombre=" + nombre + ", foto=" + foto +", fecha_produccion=" + fecha_produccion + '}';
    }

}
