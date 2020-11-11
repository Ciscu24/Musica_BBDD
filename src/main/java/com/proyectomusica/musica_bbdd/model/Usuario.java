package com.proyectomusica.musica_bbdd.model;

import java.util.List;

public class Usuario {
    protected int id;
    protected String correo;
    protected String nombre;
    protected String foto;
    protected List<Lista> listas;

    public Usuario() {}
    
    public Usuario(int id, String correo, String nombre, String foto, List<Lista> listas) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.foto = foto;
        this.listas = listas;
    }
    
    public Usuario(String correo, String nombre, String foto) {
        this.id = -1;
        this.correo = correo;
        this.nombre = nombre;
        this.foto = foto;
        this.listas = null;
    }

    public Usuario(String correo, String nombre, String foto, List<Lista> listas) {
        this.id=-1;
        this.correo = correo;
        this.nombre = nombre;
        this.foto = foto;
        this.listas = listas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public List<Lista> getListas() {
        if(listas == null){
            listas = ListaDAO.selectAll(id);
        }
        return listas;
    }

    public void setListas(List<Lista> listas) {
        this.listas = listas;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", correo=" + correo + ", nombre=" + nombre + ", foto=" + foto + '}';
    }

    public String toStringWithListas() {
        return "Usuario{" + "id=" + id + ", correo=" + correo + ", nombre=" + nombre + ", foto=" + foto + ", listas=" + listas + '}';
    }
    
    
    
}
