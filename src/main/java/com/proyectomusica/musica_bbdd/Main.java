package com.proyectomusica.musica_bbdd;

import com.proyectomusica.musica_bbdd.model.CancionDAO;
import com.proyectomusica.musica_bbdd.model.Cancion;
import com.proyectomusica.musica_bbdd.model.DiscoDAO;
import com.proyectomusica.musica_bbdd.model.Disco;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        List<Cancion> canciones = CancionDAO.selectAll();
        for(Cancion c : canciones){
            System.out.println(c.toStringWithDisco());
        }
        
        for(Cancion c : canciones){
            c.getDisco_contenedor();
            System.out.println(c.toStringWithDisco());
        }
        */
        
        /*
        List<Disco> discos = DiscoDAO.selectAll();
        
        for(Disco d : discos){
            System.out.println(d.toStringWithCanciones());
        }
        
        for(Disco d : discos){
            d.getCanciones();
            System.out.println(d.toStringWithCanciones());
        }
        */
        
        
        AppController.ejecutar();
    }
}
