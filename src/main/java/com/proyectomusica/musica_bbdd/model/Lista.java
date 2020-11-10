package com.proyectomusica.musica_bbdd.model;

import java.util.List;

public class Lista {

    protected int id;
    protected String nombre;
    protected String descripcion;
    protected Usuario creador;
    protected List<Cancion> canciones;

    public Lista() {}

    public Lista(int id, String nombre, String descripcion, Usuario creador, List<Cancion> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
        this.canciones = canciones;
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

    public Usuario getCreador() {
        if(this.creador.correo.equals("") || this.creador.nombre.equals("") || this.creador.foto.equals("")){
            creador = new UsuarioDAO().selectAllForID(creador.id);
        }
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public List<Cancion> getCanciones() {
        if(canciones == null){
            canciones = Lista_CancionDAO.selectAllCanciones(id);
        }
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    @Override
    public String toString() {
        return "Lista{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + " }";
    }
    
    public String toStringWithUsuario(){
        return "Lista{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", creador=" + creador + '}';  
    }
    
    public String toStringWithCanciones(){
        return "Lista{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", canciones=" + canciones + '}';  
    }

    public String toStringFull() {
        return "Lista{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", creador=" + creador + ", canciones=" + canciones + '}';
    }
    
    

}
