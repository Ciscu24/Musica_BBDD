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
public class Comenta {

    private int id;
    private int id_usuario;
    private int id_lista;
    private String mensaje;
    private Timestamp instante;

    public Comenta() {
    }

    public Comenta(int id, int id_usuario, int id_lista, String mensaje, Timestamp instante) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_lista = id_lista;
        this.mensaje = mensaje;
        this.instante = instante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Timestamp getInstante() {
        return instante;
    }

    public void setInstante(Timestamp instante) {
        this.instante = instante;
    }

    @Override
    public String toString() {
        return "Comenta{" + "id=" + id + ", id_usuario=" + id_usuario
                + ", id_lista=" + id_lista + ", mensaje=" + mensaje
                + ", instante=" + instante + '}';
    }

}
