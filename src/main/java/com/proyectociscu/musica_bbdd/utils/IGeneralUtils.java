package com.proyectociscu.musica_bbdd.utils;

import com.proyectociscu.musica_bbdd.model.Artista;
import com.proyectociscu.musica_bbdd.model.Cancion;
import com.proyectociscu.musica_bbdd.model.Comenta;
import com.proyectociscu.musica_bbdd.model.Disco;
import com.proyectociscu.musica_bbdd.model.Genero;
import com.proyectociscu.musica_bbdd.model.Lista;
import com.proyectociscu.musica_bbdd.model.Lista_Cancion;
import com.proyectociscu.musica_bbdd.model.Reproduce;
import com.proyectociscu.musica_bbdd.model.Subscripcion;
import com.proyectociscu.musica_bbdd.model.Usuario;

public interface IGeneralUtils {
    //Artista
    Artista devolverArtista(String nombre);
    
    
    //Genero
    Genero devolverGenero(String nombre);
    
    
    //Disco
    Disco devolverDisco(String nombre);
    
    
    //Cancion
    Cancion devolverCancion(String nombre);
    
    
    //Lista
    Lista devolverLista(String nombre);
    
    //Usuario
    Usuario devolverUsuario(String nombre);
    
    

    //Lista_Cancion
    //Lista_Cancion devolverListaCancion();
    
    
    //Comenta
    //Comenta devolverComentario(String nombre);
    
    
    //Reproduce
    //Reproduce devolverReproduccion(String nombre);
    
    
    //Subscripcion
    //Subscripcion devolverSubscripcion(String nombre);

}
