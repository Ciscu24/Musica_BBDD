package com.proyectomusica.musica_bbdd;

import com.proyectomusica.musica_bbdd.model.Artista;
import com.proyectomusica.musica_bbdd.model.ArtistaDAO;
import com.proyectomusica.musica_bbdd.model.Disco;
import com.proyectomusica.musica_bbdd.model.DiscoDAO;
import com.proyectomusica.musica_bbdd.utils.Utils;
import static com.proyectomusica.musica_bbdd.utils.Utils.devolverString;
import static com.proyectomusica.musica_bbdd.utils.Utils.devolverInt;
import static com.proyectomusica.musica_bbdd.utils.Utils.pulsarEnter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppControllerCisquito {

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
                System.out.println("| 4) Lista de Reproduccion  |");
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

                    case 4:
                        Menu_Listas();
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
                    int idArtistaEliminado = devolverInt("Introduzca el id del artista que desea cambiar: ");

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
            System.out.println("\n+------------------+");
            System.out.println("|    Menu Discos   |");
            System.out.println("+------------------+");
            System.out.println("| 1) Crear disco   |");
            System.out.println("| 2) Editar disco  |");
            System.out.println("| 3) Borrar disco  |");
            System.out.println("| 0) Salir         |");
            System.out.println("+------------------+");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    String nombre = devolverString("Introduzca el nombre del disco: ");
                    String foto = devolverString("Introduzca la foto del disco: ");
                    String nombre_artista = devolverString("Introduzca el nombre completo del artista que ha producido el disco: ");
                    String fecha_pro = devolverString("Introduzca la fecha de produccion del disco (Ejemplo: 2020-10-20): ");
                    Artista artista_disco = ArtistaDAO.selectAllForNombre(nombre_artista);
                    if(artista_disco!=null){
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
                    int idArtistaEliminado = devolverInt("Introduzca el id del artista que desea cambiar: ");

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

    public static void Menu_Canciones() {

    }

    public static void Menu_Listas() {

    }

}
