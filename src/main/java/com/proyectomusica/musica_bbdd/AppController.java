package com.proyectomusica.musica_bbdd;

import com.proyectomusica.musica_bbdd.model.Artista;
import com.proyectomusica.musica_bbdd.model.ArtistaDAO;
import com.proyectomusica.musica_bbdd.model.Cancion;
import com.proyectomusica.musica_bbdd.model.CancionDAO;
import com.proyectomusica.musica_bbdd.model.Disco;
import com.proyectomusica.musica_bbdd.model.DiscoDAO;
import com.proyectomusica.musica_bbdd.model.Lista_CancionDAO;
import com.proyectomusica.musica_bbdd.model.Subscripcion;
import com.proyectomusica.musica_bbdd.model.Usuario;
import com.proyectomusica.musica_bbdd.model.Lista;
import com.proyectomusica.musica_bbdd.model.ListaDAO;
import com.proyectomusica.musica_bbdd.model.UsuarioDAO;
import com.proyectomusica.musica_bbdd.utils.ConnectionUtil;
import static com.proyectomusica.musica_bbdd.utils.Utils.devolverInt;
import static com.proyectomusica.musica_bbdd.utils.Utils.devolverString;
import java.util.List;
import java.util.Scanner;

import static com.proyectomusica.musica_bbdd.utils.Utils.pulsarEnter;
import java.util.ArrayList;

public class AppController {

    /*
    public static List<Artista> artistas = ArtistaDAO.selectAll();
    public static List<Cancion> canciones = null;
    public static List<Disco> discos = DiscoDAO.selectAll();
    public static List<Lista> listas = null;
    public static List<Lista_Cancion> listas_canciones = null;
    public static List<Subscripcion> subcripciones = null;
    public static List<Usuario> usuarios = null;
     */
    public static void ejecutar() {
        //Funciona para probar los DAO

        //List<Lista> listado = ListaDAO.selectAll();
        // Lista l = listado.get(0);
        //System.out.println(l);
        // l.getCreador();
        // System.out.println(l);
        ///////
        /*List<Disco> discos = DiscoDAO.selectAll();
        //System.out.println(discos);

        Disco d = discos.get(0);
        System.out.println(d.toStringWithCanciones());
        d.getCreador();
        d.getCanciones();
        System.out.println(d.toStringWithCanciones());
        
        
        System.out.println(d.getCanciones());*/
        //  List<Lista> listas = ListaDAO.selectAll();
        //  System.out.println(listas.get(0).toStringFull());
        //  listas.get(0).getCanciones();
        // listas.get(0).getCreador();
        // System.out.println(listas.get(0).toStringFull());
        //System.out.println(ArtistaDAO.selectAllForId(4));
        List<Artista> ciscu = ArtistaDAO.selectAll();
        ciscu.get(3).getDisco();
        System.out.println(ciscu);

        //principal();
        lista_discos();

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

            System.out.println("Introduce una opción");
            numero = teclado.nextInt();
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
        System.out.println(UsuarioDAO.selectAll());

        System.out.println("\n+-------------------+");
        System.out.println("|   Iniciar Sesion  |");
        System.out.println("+-------------------+");
        String usuario = devolverString("Introduce tu usuario: ");
        String contrasena = devolverString("Introduce tu correo: ");

        List<Usuario> Buscar = UsuarioDAO.selectAll(usuario);
        if (usuario != null && contrasena != null) {
        }
        boolean bandera = false;

        for (int i = 0; i < Buscar.size() && !bandera; i++) {

            if (Buscar.get(i).getNombre().equals(usuario)) {
                System.out.println("Encontrado");
                bandera = true;
                lista_sesion(usuario);

            }
        }

    }

    public static void registrarse() {
        System.out.println("\n+-------------------+");
        System.out.println("|    Registrarse    |");
        System.out.println("+-------------------+");
        String nombre = devolverString("Introduce tu nombre: ");
        String correo = devolverString("Introduce un correo: ");
        String foto = devolverString("Introduce una foto");
        if (nombre.equals("") && correo.equals("") && foto.equals("")) {
            System.out.println("No se ha podido realizar el registro");
        } else {
            UsuarioDAO usuario = new UsuarioDAO(correo, nombre, foto);
            usuario.save();
            System.out.println("Usuario creado");
        }
        pulsarEnter();
    }

    public static void lista_sesion(String usuario) {
        if (usuario.equals("Admin")) {
            int opcion = 0;
            do {
                System.out.println("\n+---------------------------+");
                System.out.println("|    Menu administrador     |");
                System.out.println("+---------------------------+");
                System.out.println("| 1) Listar Discos          |");
                System.out.println("| 2) Suscripciones          |");
                System.out.println("| 3) Listar de Reproducion  |");
                System.out.println("| 0) Salir                  |");
                System.out.println("+---------------------------+");

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
        int opcion = 0;
        do {
            System.out.println("\n+----------------------------+");
            System.out.println("|    Menu Usuario            |");
            System.out.println("+----------------------------+");
            System.out.println("| 1) Listar Discos           |");
            System.out.println("| 2) Suscripciones           |");
            System.out.println("| 3) Listar de Reproduccion  |");
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

    public static void prueba() {
        List<String> s = new ArrayList<String>();
        s.add("edu");
        s.add("anto");
        s.add("ciscu");
        s.add("rafa");
        s.add("anto");
        boolean bandera = false;

        for (int i = 0; i < s.size() && !bandera; i++) {
            System.out.println(s.get(i));
            if (s.get(i).equals("anto")) {
                System.out.println("Hola");
                bandera = true;

            }
        }

    }
}
