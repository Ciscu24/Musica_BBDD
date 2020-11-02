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
public class Subscripcion {

    private int id_usuario;
    private int id_lista;

    public Subscripcion() {
    }

    public Subscripcion(int id_usuario, int id_lista) {
        this.id_usuario = id_usuario;
        this.id_lista = id_lista;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }

    @Override
    public String toString() {
        return "Subscripcion{" + "id_usuario=" + id_usuario + ", id_lista=" + id_lista + '}';
    }

}
