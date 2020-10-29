package com.proyectociscu.model;

public class Lista_Cancion {

    private int id_lista;
    private int id_cancion;

    public Lista_Cancion() {
    }

    public Lista_Cancion(int id_lista, int id_cancion) {
        this.id_lista = id_lista;
        this.id_cancion = id_cancion;
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }

    public int getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(int id_cancion) {
        this.id_cancion = id_cancion;
    }

    @Override
    public String toString() {
        return "Lista_Cancion{" + "id_lista=" + id_lista + ", id_cancion=" + id_cancion + '}';
    }

}
