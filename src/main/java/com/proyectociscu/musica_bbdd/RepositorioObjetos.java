package com.proyectociscu.musica_bbdd;

import com.proyectociscu.musica_bbdd.model.Artista;
import com.proyectociscu.musica_bbdd.model.ArtistaDAO;
import java.util.List;

public class RepositorioObjetos {
    public static List<Artista> artistas = ArtistaDAO.selectAll();
    public static List<Cancion> = null;
    public static List<Comenta> = null;
    public static List<Disco> = null;
    public static List<Genero> = null;
    public static List<Lista> = null;
    public static List<Lista_Cancion> = null;
    public static List<Reproduce> = null;
    public static List<Subscripcion> = null;
    public static List<Ususario> = null;
}
