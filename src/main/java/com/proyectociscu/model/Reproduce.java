package com.proyectociscu.model;

import java.sql.Timestamp;

public class Reproduce {

    private int id;
    private int id_usuario;
    private int id_cancion;
    private Timestamp instante;

    public Reproduce() {
    }

    public Reproduce(int id, int id_usuario, int id_cancion, Timestamp instante) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_cancion = id_cancion;
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

    public int getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(int id_cancion) {
        this.id_cancion = id_cancion;
    }

    public Timestamp getInstante() {
        return instante;
    }

    public void setInstante(Timestamp instante) {
        this.instante = instante;
    }

    @Override
    public String toString() {
        return "Reproduce{" + "id=" + id + ", id_usuario=" + id_usuario + ", id_cancion=" + id_cancion + ", instante=" + instante + '}';
    }

}
