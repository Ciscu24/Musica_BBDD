package com.proyectomusica.musica_bbdd;

import com.proyectomusica.musica_bbdd.model.Artista;
import com.proyectomusica.musica_bbdd.model.ArtistaDAO;
import com.proyectomusica.musica_bbdd.model.Cancion;
import com.proyectomusica.musica_bbdd.model.CancionDAO;
import com.proyectomusica.musica_bbdd.model.Disco;
import com.proyectomusica.musica_bbdd.model.DiscoDAO;
import com.proyectomusica.musica_bbdd.model.Lista_CancionDAO;
import com.proyectomusica.musica_bbdd.model.SuscripcionDAO;
import com.proyectomusica.musica_bbdd.model.Usuario;
import com.proyectomusica.musica_bbdd.model.Lista;
import com.proyectomusica.musica_bbdd.model.ListaDAO;
import com.proyectomusica.musica_bbdd.model.UsuarioDAO;
import com.proyectomusica.musica_bbdd.utils.ConnectionUtil;
import static com.proyectomusica.musica_bbdd.utils.Utils.devolverInt;
import static com.proyectomusica.musica_bbdd.utils.Utils.devolverString;
import static com.proyectomusica.musica_bbdd.utils.Utils.pulsarEnter;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;

public class AppController {
    
    public static void ejecutar() {
        principal();
    }

    public static void principal() {

        int numero;

        do {
            Scanner teclado = new Scanner(System.in);
            System.out.println("\n+-------------------+");
            System.out.println("|        Menu       |");
            System.out.println("+-------------------+");
            System.out.println("| 1) Iniciar sesion |");
            System.out.println("| 2) Registrarse    |");
            System.out.println("| 3) Empleados      |");
            System.out.println("| 0) Salir          |");
            System.out.println("+-------------------+");

            numero = devolverInt("Introduce una opción: ");
            opciones_principal(numero);
        } while (numero != 0);
    }

    static void opciones_principal(int numero) {
        switch (numero) {
            case 1: //Caso 1 para 
                Iniciar_sesion();
                break;

            case 2: //Caso 2 para 
                registrarse();
                break;

            case 3: //Caso 3 para 
                Iniciar_sesion();
                break;

        }
    }

    public static void Iniciar_sesion() {

        boolean result = false;
        //System.out.println(UsuarioDAO.selectAll());

        System.out.println("\n+-------------------+");
        System.out.println("|   Iniciar Sesion  |");
        System.out.println("+-------------------+");
        String nombre = devolverString("Introduce tu nombre: ");
        String correo = devolverString("Introduce tu correo: ");

        
        if (nombre.equals("") || correo.equals("")) {
            System.out.println("Usted no ha introducido nada");
            pulsarEnter();
        }else{
            List<Usuario> Buscar = UsuarioDAO.selectAll(nombre);
            
            boolean bandera = false;
            for (int i = 0; i < Buscar.size() && !bandera; i++) {
                if (Buscar.get(i).getNombre().equals(nombre) && Buscar.get(i).getCorreo().equals(correo)) {
                    bandera = true;
                }
            }
            if(bandera == true){
                lista_sesion(nombre);
            }else{
                System.out.println("Ususario o correo no validos");
                pulsarEnter();
            }
        }
        
        

    }

    public static void registrarse() {
        System.out.println("\n+-------------------+");
        System.out.println("|    Registrarse    |");
        System.out.println("+-------------------+");
        String nombre = devolverString("Introduce tu nombre: ");
        String correo = devolverString("Introduce un correo: ");
        String foto = devolverString("Introduce una foto: ");
        if (nombre.equals("") || correo.equals("") || foto.equals("")) {
            System.out.println("No se ha podido realizar el registro");
        } else {
            UsuarioDAO usuario = new UsuarioDAO(correo, nombre, foto);
            usuario.save();
            System.out.println("Usuario creado");
        }
        pulsarEnter();
    }

    public static void lista_discos() {
        int opcion = 0;
        do {
            System.out.println("\n+--------------------------------------+");
            System.out.println("|             Menu Listar              |");
            System.out.println("+--------------------------------------+");
            System.out.println("| 1) Listar Discos por autor           |");
            System.out.println("| 2) Listar discos por nombre de disco |");
            System.out.println("| 3) Listar Todos                      |");
            System.out.println("| 0) Salir                             |");
            System.out.println("+--------------------------------------+");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    String patternn = devolverString("Introduce el nombre del autor: ");

                    List<Artista> artista = ArtistaDAO.selectAll(patternn);
                    List<Disco> disco = new ArrayList<>();

                    for (Artista a : artista) {
                        System.out.println("El artista que hemos encontrado es: ");
                        System.out.println(a.getNombre());
                        System.out.println("Su discos son: ");
                        for (int i = 0; i < a.getDisco().size(); i++) {
                            System.out.println(i + 1 + ".- " + a.getDisco().get(i));
                            disco.add(a.getDisco().get(i));
                        }
                    }

                    int opcion1 = devolverInt("Introduce el numero de la lista para ver las canciones o 0 para salir : ");

                    if (opcion1 != 0) {
                        System.out.println(disco.get(opcion1 - 1).getCanciones());
                        pulsarEnter();
                    } else {
                        System.out.println("Listar finalizado");
                        pulsarEnter();
                    }
                    break;

                case 2:
                    String pattern = devolverString("Introduce el nombre del disco: ");
                    List<Disco> disco_nombre = DiscoDAO.selectAll(pattern);

                    if (disco_nombre.size() == 0) {
                        System.out.println("No se han encontrado discos");
                    } else {
                        for (int i = 0; i < disco_nombre.size(); i++) {
                            System.out.println("El disco que hemos encontrado es: ");
                            System.out.println(i + 1 + ".- " + disco_nombre.get(i));
                        }

                        int opcion2 = devolverInt("Introduce el numero de la lista para ver las canciones o 0 para salir : ");

                        if (opcion2 != 0) {
                            System.out.println(disco_nombre.get(opcion2 - 1).getCanciones());
                            pulsarEnter();
                        } else {
                            System.out.println("Listar finalizado");
                            pulsarEnter();
                        }
                    }
                    break;

                case 3:
                    List<Disco> discos = DiscoDAO.selectAll();

                    if (discos.size() == 0) {
                        System.out.println("Error");
                    } else {
                        for (int i = 0; i < discos.size(); i++) {
                            System.out.println("El disco que hemos encontrado es: ");
                            System.out.println(i + 1 + ".- " + discos.get(i));

                        }
                        int opcion3 = devolverInt("Introduce el numero de la lista para ver las canciones o 0 para salir : ");

                        if (opcion3 != 0) {
                            System.out.println(discos.get(opcion3 - 1).getCanciones());
                            pulsarEnter();
                        } else {
                            System.out.println("Listar finalizado");
                            pulsarEnter();
                        }
                        break;
                    }
            }
        } while (opcion != 0);
    }
    
    public static void lista_sesion(String usuario) {
        if (usuario.equals("Admin")) {
            int opcion = 0;
            do {
                System.out.println("\n+---------------------------+");
                System.out.println("|    Menu administrador     |");
                System.out.println("+---------------------------+");
                System.out.println("| 1) Artistas               |");
                System.out.println("| 2) Discos                 |");
                System.out.println("| 3) Cancion                |");
                System.out.println("| 0) Salir                  |");
                System.out.println("+---------------------------+");

                opcion = devolverInt("Introduce una opcion: ");

                switch (opcion) {
                    case 1:
                        Menu_Artistas();
                        break;

                    case 2:
                        Menu_Discos();
                        break;

                    case 3:
                        Menu_Canciones();
                        break;
                }
            } while (opcion != 0);
        }else{
            int opcion = 0;
            do {
                System.out.println("\n+----------------------------+");
                System.out.println("|    Menu Usuario            |");
                System.out.println("+----------------------------+");
                System.out.println("| 1) Listar Discos           |");
                System.out.println("| 2) Lista de Reproduccion   |");
                System.out.println("| 3) Suscripciones           |");
                System.out.println("| 0) Salir                   |");
                System.out.println("+----------------------------+");

                opcion = devolverInt("Introduce una opcion: ");

                switch (opcion) {
                    case 1:
                        lista_discos();
                        break;

                    case 2:
                        break;

                    case 3:

                        break;

                }
            } while (opcion != 0);
        }
    }

    public static void Menu_Artistas() {
        int opcion = 0;
        do {
            System.out.println("\n+--------------------+");
            System.out.println("|    Menu Artistas   |");
            System.out.println("+--------------------+");
            System.out.println("| 1) Crear artista   |");
            System.out.println("| 2) Editar artista  |");
            System.out.println("| 3) Borrar artista  |");
            System.out.println("| 0) Salir           |");
            System.out.println("+--------------------+");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    String nombre = devolverString("Introduzca el nombre del artista: ");
                    String nacionalidad = devolverString("Introduzca la nacionalidad del artista: ");
                    String foto = devolverString("Introduzca la foto del artista: ");
                    Artista a = new Artista(nombre, nacionalidad, foto, null);
                    ArtistaDAO aDAO = new ArtistaDAO(a);
                    if (aDAO.save() != -1) {
                        System.out.println("El artista se ha creado con exito");
                    } else {
                        System.out.println("No se ha podido crear el artista");
                    }
                    break;

                case 2:
                    List<Artista> artistas = ArtistaDAO.selectAll();
                    for (Artista art : artistas) {
                        System.out.println(art);
                    }
                    int idArtista = devolverInt("Introduzca el id del artista que desea cambiar: ");
                    Artista art = ArtistaDAO.selectAllForId(idArtista);

                    if (art.getId() != -1) {
                        int opcion1 = 0;
                        do {
                            System.out.println("\n+--------------------------------+");
                            System.out.println("|   Artista: " + art.getId() + "                  |");
                            System.out.println("+--------------------------------+");
                            System.out.println(" 1) Editar nombre: " + art.getNombre());
                            System.out.println(" 2) Editar nacionalidad: " + art.getNacionalidad());
                            System.out.println(" 3) Editar foto: " + art.getFoto());
                            System.out.println(" 0) Guardar artista");

                            opcion1 = devolverInt("Introduce una opcion: ");

                            switch (opcion1) {
                                case 1:
                                    String nombreCambio = devolverString("Introduce el nuevo nombre: ");
                                    art.setNombre(nombreCambio);
                                    break;
                                case 2:
                                    String nacionalidadCambio = devolverString("Introduce la nueva nacionalidad: ");
                                    art.setNacionalidad(nacionalidadCambio);
                                    break;
                                case 3:
                                    String fotoCambio = devolverString("Introduce la nueva foto: ");
                                    art.setFoto(fotoCambio);
                                    break;
                                case 0:
                                    ArtistaDAO artistaDAO = new ArtistaDAO(art);
                                    if (artistaDAO.save() != -1) {
                                        System.out.println("Artista guardado con exito");
                                    } else {
                                        System.out.println("El artista no se ha guardado");
                                    }
                                    break;
                            }
                        } while (opcion1 != 0);
                    } else {
                        System.out.println("Usted ha introducido un numero incorrecto");
                        pulsarEnter();
                    }

                    break;

                case 3:
                    List<Artista> artistasEliminar = ArtistaDAO.selectAll();
                    for (Artista artistaEliminado : artistasEliminar) {
                        System.out.println(artistaEliminado);
                    }
                    int idArtistaEliminado = devolverInt("Introduzca el id del artista que desea eliminar: ");

                    ArtistaDAO artistaElminado = new ArtistaDAO(ArtistaDAO.selectAllForId(idArtistaEliminado));

                    if (artistaElminado.getId() != -1 && artistaElminado.remove() != -1) {
                        System.out.println("El artista ha sido borrado con exito");
                    } else {
                        System.out.println("El artista no se ha borrado");
                    }

                    break;
            }
        } while (opcion != 0);
    }

    public static void Menu_Discos() {
        int opcion = 0;
        do {
            System.out.println("\n+-------------------+");
            System.out.println("|    Menu Discos    |");
            System.out.println("+-------------------+");
            System.out.println("| 1) Crear disco    |");
            System.out.println("| 2) Editar disco   |");
            System.out.println("| 3) Borrar disco   |");
            System.out.println("| 4) Listar discos  |");
            System.out.println("| 0) Salir          |");
            System.out.println("+-------------------+");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    String nombre = devolverString("Introduzca el nombre del disco: ");
                    String foto = devolverString("Introduzca la foto del disco: ");
                    String nombre_artista = devolverString("Introduzca el nombre completo del artista que ha producido el disco: ");
                    String fecha_pro = devolverString("Introduzca la fecha de produccion del disco (Ejemplo: 2020-10-20): ");
                    Artista artista_disco = ArtistaDAO.selectAllForNombre(nombre_artista);
                    if (artista_disco != null) {
                        Disco d = new Disco(nombre, foto, artista_disco, Date.valueOf(fecha_pro), null);
                        DiscoDAO dDAO = new DiscoDAO(d);
                        if (dDAO.save() != -1) {
                            System.out.println("El artista se ha creado con exito");
                        } else {
                            System.out.println("No se ha podido crear el artista");
                        }
                    }
                    break;

                case 2:
                    List<Disco> discos = DiscoDAO.selectAll();
                    for (Disco d : discos) {
                        System.out.println(d);
                    }
                    int idDisco = devolverInt("Introduzca el id del disco que desea cambiar: ");
                    Disco d = DiscoDAO.selectAllForId(idDisco);

                    if (d != null) {
                        int opcion1 = 0;
                        do {
                            System.out.println("\n+--------------------------------+");
                            System.out.println("|   Disco: " + d.getId() + "                  |");
                            System.out.println("+--------------------------------+");
                            System.out.println(" 1) Editar nombre: " + d.getNombre());
                            System.out.println(" 2) Editar foto: " + d.getFoto());
                            System.out.println(" 3) Editar fecha de produccion: " + d.getFecha_produccion());
                            System.out.println(" 4) Editar creador: " + d.getCreador().getNombre());
                            System.out.println(" 0) Guardar artista");

                            opcion1 = devolverInt("Introduce una opcion: ");

                            switch (opcion1) {
                                case 1:
                                    String nombreCambio = devolverString("Introduce el nuevo nombre: ");
                                    d.setNombre(nombreCambio);
                                    break;
                                case 2:
                                    String fotoCambio = devolverString("Introduce la nueva foto: ");
                                    d.setFoto(fotoCambio);
                                    break;
                                case 3:
                                    String fechaCambio = devolverString("Introduce la nueva fecha de produccion: ");
                                    d.setFecha_produccion(Date.valueOf(fechaCambio));
                                    break;
                                case 4:
                                    String nombreCreadorCambio = devolverString("Introduce el nuevo nombre del creador: ");
                                    Artista artistacambio = ArtistaDAO.selectAllForNombre(nombreCreadorCambio);
                                    if (artistacambio != null) {
                                        d.setCreador(artistacambio);
                                    } else {
                                        System.out.println("No se ha encontrado el artista");
                                    }
                                    break;
                                case 0:
                                    DiscoDAO discoDAO = new DiscoDAO(d);
                                    if (discoDAO.save() != -1) {
                                        System.out.println("Disco guardado con exito");
                                    } else {
                                        System.out.println("El disco no se ha guardado");
                                    }
                                    break;
                            }
                        } while (opcion1 != 0);
                    } else {
                        System.out.println("Usted ha introducido un numero incorrecto");
                        pulsarEnter();
                    }

                    break;

                case 3:
                    List<Disco> discosEliminar = DiscoDAO.selectAll();
                    for (Disco discoEliminar : discosEliminar) {
                        System.out.println(discoEliminar);
                    }
                    int idDiscoEliminado = devolverInt("Introduzca el id del disco que desea eliminar: ");

                    DiscoDAO discoEliminado = new DiscoDAO(DiscoDAO.selectAllForId(idDiscoEliminado));

                    if (discoEliminado.getId() != -1 && discoEliminado.remove() != -1) {
                        System.out.println("El disco ha sido borrado con exito");
                    } else {
                        System.out.println("El disco no se ha borrado");
                    }

                    break;

                case 4:
                    List<Disco> AllDiscos = DiscoDAO.selectAll();
                    for (Disco ds : AllDiscos) {
                        System.out.println(ds.toStringAll());
                    }
                    break;

            }
        } while (opcion != 0);
    }

    public static void Menu_Canciones() {
        int opcion = 0;
        do {
            System.out.println("\n+---------------------+");
            System.out.println("|    Menu Canciones   |");
            System.out.println("+---------------------+");
            System.out.println("| 1) Crear cancion    |");
            System.out.println("| 2) Editar cancion   |");
            System.out.println("| 3) Borrar cancion   |");
            System.out.println("| 4) Listar cancion   |");
            System.out.println("| 0) Salir            |");
            System.out.println("+---------------------+");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    String nombre = devolverString("Introduzca el nombre de la cancion: ");
                    int duracion = devolverInt("Introduzca la duracion de la cancion: ");
                    int id_disco = devolverInt("Introduzca el id del disco: ");
                    Disco cancion_disco = DiscoDAO.selectAllForId(id_disco);
                    if (cancion_disco != null) {
                        Cancion c = new Cancion(nombre, duracion, cancion_disco);
                        CancionDAO cDAO = new CancionDAO(c);
                        if (cDAO.save() != -1) {
                            System.out.println("El artista se ha creado con exito");
                        } else {
                            System.out.println("No se ha podido crear el artista");
                        }
                    }
                    break;

                case 2:
                    List<Cancion> canciones = CancionDAO.selectAll();
                    for (Cancion d : canciones) {
                        System.out.println(d);
                    }
                    int idcancion = devolverInt("Introduzca el id de la cancion que desea cambiar: ");
                    Cancion c = CancionDAO.selectAllForId(idcancion);

                    if (c != null) {
                        int opcion1 = 0;
                        do {
                            System.out.println("\n+--------------------------------+");
                            System.out.println("|   Disco: " + c.getId() + "                  |");
                            System.out.println("+--------------------------------+");
                            System.out.println(" 1) Editar nombre: " + c.getNombre());
                            System.out.println(" 2) Editar duracion: " + c.getDuracion());
                            System.out.println(" 3) Editar disco: " + c.getDisco_contenedor().getNombre());
                            System.out.println(" 0) Guardar Cancion");

                            opcion1 = devolverInt("Introduce una opcion: ");

                            switch (opcion1) {
                                case 1:
                                    String nombreCambio = devolverString("Introduce el nuevo nombre: ");
                                    c.setNombre(nombreCambio);
                                    break;
                                case 2:
                                    int duracion1 = devolverInt("Introduce la nueva duracion: ");
                                    c.setDuracion(duracion1);
                                    break;
                                case 3:
                                    String NombreDiscoCambio = devolverString("Introduce el nuevo nombre del disco: ");
                                    Disco Discocambio = DiscoDAO.selectAllForNombre(NombreDiscoCambio);
                                    if (Discocambio != null) {
                                        c.setDisco_contenedor(Discocambio);
                                    } else {
                                        System.out.println("No se ha encontrado el disco");
                                    }
                                    break;
                                case 0:
                                    CancionDAO cancionDAO = new CancionDAO(c);
                                    if (cancionDAO.save() != -1) {
                                        System.out.println("Cancion guardado con exito");
                                    } else {
                                        System.out.println("La cancion no se ha guardado");
                                    }
                                    break;
                            }
                        } while (opcion1 != 0);
                    } else {
                        System.out.println("Usted ha introducido un numero incorrecto");
                        pulsarEnter();
                    }

                    break;

                case 3:
                    List<Cancion> cancionEliminar=CancionDAO.selectAll();
                    for (Cancion cancionElimina : cancionEliminar) {
                        System.out.println(cancionElimina);
                    }
                    int idCancionEliminado = devolverInt("Introduzca el id de la cancion que desea eliminar: ");

                    CancionDAO cancionEliminado = new CancionDAO(CancionDAO.selectAllForId(idCancionEliminado));

                    if (cancionEliminado.getId() != -1 && cancionEliminado.remove() != -1) {
                        System.out.println("El disco ha sido borrado con exito");
                    } else {
                        System.out.println("El disco no se ha borrado");
                    }
                    break;

                case 4:
                    List<Cancion> AllCanciones = CancionDAO.selectAll();
                    for (Cancion ds : AllCanciones) {
                        System.out.println(ds.toString());
                    }
                    break;
            }
        } while (opcion != 0);

    }

}
