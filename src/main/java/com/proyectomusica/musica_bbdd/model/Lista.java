package com.proyectomusica.musica_bbdd.model;

public class Lista {

    protected int id;
    protected String nombre;
    protected String descripcion;
    protected Usuario creador;

    public Lista() {}

    public Lista(int id, String nombre, String descripcion, Usuario creador) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creador = creador;
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
            creador = new UsuarioDAO().selectAllForID(id);
        }
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    @Override
    public String toString() {
        return "Lista{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", creador=" + creador + '}';
    }

}
