package com.proyectomusica.musica_bbdd.model;

public class Cancion {

    protected int id;
    protected String nombre;
    protected int duracion;
    protected Disco disco_contenedor;
    
    public Cancion() {}

    public Cancion(int id, String nombre, int duracion, Disco disco_contenedor) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.disco_contenedor = disco_contenedor;
    }

    public Cancion(String nombre, int duracion, Disco disco_contenedor) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.disco_contenedor = disco_contenedor;
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Disco getDisco_contenedor() {
        if(disco_contenedor.nombre.equals("")){
            disco_contenedor = DiscoDAO.selectAllForId(disco_contenedor.id);
        }
        return disco_contenedor;
    }

    public void setDisco_contenedor(Disco disco_contenedor) {
        this.disco_contenedor = disco_contenedor;
    }

    @Override
    public String toString() {
        return "Cancion{" + "id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + "}";
    }
    
    public String toStringWithDisco() {
        return "Cancion{" + "id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", disco_contenedor=" + disco_contenedor + '}';
    }

}
