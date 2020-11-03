package com.proyectociscu.musica_bbdd;

import com.proyectociscu.musica_bbdd.model.Artista;
import com.proyectociscu.musica_bbdd.model.ArtistaDAO;
import com.proyectociscu.musica_bbdd.model.Cancion;
import com.proyectociscu.musica_bbdd.model.Disco;
import com.proyectociscu.musica_bbdd.model.Lista_Cancion;
import com.proyectociscu.musica_bbdd.model.Subscripcion;
import com.proyectociscu.musica_bbdd.model.Usuario;
import com.proyectociscu.musica_bbdd.model.Lista;
import java.util.List;

public class RepositorioObjetos {

    public static List<Artista> artistas = ArtistaDAO.selectAll();
    public static List<Cancion> canciones = null;
    public static List<Disco> discos = null;
    public static List<Lista> listas = null;
    public static List<Lista_Cancion> listas_canciones = null;
    public static List<Subscripcion> subcripciones = null;
    public static List<Usuario> usuarios = null;
}
