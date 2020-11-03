package com.proyectomusica.musica_bbdd.model;

public class Artista {

    private int id;
    private String nombre;
    private String nacionalidad;
    private String foto;
    

    public Artista() {
        this(-1,"","","");
    }
    
    public Artista(String nombre, String nacionalidad, String foto){
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.foto = foto;
    }

    public Artista(int id, String nombre, String nacionalidad, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.foto = foto;
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
        return "artista{" + "id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", foto=" + foto + '}';
    }

}
