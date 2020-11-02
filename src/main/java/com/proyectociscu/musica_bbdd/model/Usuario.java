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
public class Usuario {
    private int id;
    private String correo;
    private String nombre;
    private String foto;

    public Usuario() {
    }

    public Usuario(int id, String correo, String nombre, String foto) {
        this.id = id;
        this.correo = correo;
        this.nombre = nombre;
        this.foto = foto;
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

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", correo=" + correo + ", nombre=" + nombre + ", foto=" + foto + '}';
    }
    
    
    
}
