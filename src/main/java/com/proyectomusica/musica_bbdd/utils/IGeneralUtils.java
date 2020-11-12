package com.proyectomusica.musica_bbdd.utils;

import com.proyectomusica.musica_bbdd.model.Artista;
import com.proyectomusica.musica_bbdd.model.Cancion;
import com.proyectomusica.musica_bbdd.model.Disco;
import com.proyectomusica.musica_bbdd.model.Lista;
import com.proyectomusica.musica_bbdd.model.Lista_CancionDAO;
import com.proyectomusica.musica_bbdd.model.SuscripcionDAO;
import com.proyectomusica.musica_bbdd.model.Usuario;

public interface IGeneralUtils {
    //Artista
    Artista devolverArtista(String nombre);
    
    //Disco
    Disco devolverDisco(String nombre);
    
    
    //Cancion
    Cancion devolverCancion(String nombre);
    
    
    //Lista
    Lista devolverLista(String nombre);
    
    //Usuario
    Usuario devolverUsuario(String nombre);
    

}
