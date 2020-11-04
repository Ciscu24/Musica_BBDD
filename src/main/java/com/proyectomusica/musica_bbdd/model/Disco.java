package com.proyectomusica.musica_bbdd.model;

import java.sql.Timestamp;
import java.util.List;

public class Disco {

    protected int id;
    protected String nombre;
    protected String foto;
    protected Artista creador;
    protected Timestamp fecha_produccion;
    protected List<Cancion> canciones;

    public Disco(int id) {
        this.id = id;
        this.nombre = "";
        this.foto = "";
        this.creador = null;
        this.fecha_produccion = null;
        this.canciones = null;
    }

    public Disco() {
        this(-1, "", "", null, null, null);
    }

    public Disco(int id, String nombre, String foto, Artista creador, Timestamp fecha_produccion, List<Cancion> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.creador = creador;
        this.fecha_produccion = fecha_produccion;
        this.canciones = canciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artista getCreador() {
        
        if(creador.nombre.equals("")){        
           creador= ArtistaDAO.selectAllForId(id);
        }
        return creador;
    }

    public void setCreador(Artista creador) {
        this.creador = creador;
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

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
    
    

    @Override
    public String toString() {
        return "Disco{" + "id=" + id + ", nombre=" + nombre + ", foto=" + foto + ", artistas=" + creador + ", fecha_produccion=" + fecha_produccion + ", canciones=" + canciones + "}";
    }

}
