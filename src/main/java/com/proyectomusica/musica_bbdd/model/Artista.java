package com.proyectomusica.musica_bbdd.model;

public class Artista {

    protected int id;
    protected String nombre;
    protected String nacionalidad;
    protected String foto;
    protected Disco[] disco;
    
    public Artista() {
        this(-1,"","","",null);
    }
    
    public Artista(String nombre, String nacionalidad, String foto,Disco[] disco){
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.foto = foto;
        this.disco=disco;
    }

    public Artista(int id, String nombre, String nacionalidad, String foto, Disco[] disco) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.foto = foto;
        this.disco=disco;
    }

    public Disco[] getDisco() {
        
        return disco;
    }

    public void setDisco(Disco[] disco) {
        this.disco = disco;
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

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Artista{" + "id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", foto=" + foto + ", disco=" + disco + '}';
    }
    
    



}
