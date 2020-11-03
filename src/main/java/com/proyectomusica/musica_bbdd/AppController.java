package com.proyectomusica.musica_bbdd;

import com.proyectomusica.musica_bbdd.model.Artista;
import com.proyectomusica.musica_bbdd.model.ArtistaDAO;
import com.proyectomusica.musica_bbdd.model.Cancion;
import com.proyectomusica.musica_bbdd.model.Disco;
import com.proyectomusica.musica_bbdd.model.DiscoDAO;
import com.proyectomusica.musica_bbdd.model.Lista_Cancion;
import com.proyectomusica.musica_bbdd.model.Subscripcion;
import com.proyectomusica.musica_bbdd.model.Usuario;
import com.proyectomusica.musica_bbdd.model.Lista;
import com.proyectomusica.musica_bbdd.model.ListaDAO;
import java.util.List;

public class AppController {

    public static List<Artista> artistas = ArtistaDAO.selectAll();
    public static List<Cancion> canciones = null;
    public static List<Disco> discos = DiscoDAO.selectAll();
    public static List<Lista> listas = null;
    public static List<Lista_Cancion> listas_canciones = null;
    public static List<Subscripcion> subcripciones = null;
    public static List<Usuario> usuarios = null;
    
    public static void ejecutar(){
        //Funciona para probar los DAO
        
        List<Lista> listado = ListaDAO.selectAll();
        
        //System.out.println(listado);
        
        Lista l = listado.get(0);
        
        System.out.println(l);
        
        l.getCreador();
        
        System.out.println(l);
    }
}
