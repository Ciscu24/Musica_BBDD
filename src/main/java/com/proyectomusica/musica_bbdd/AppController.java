package com.proyectomusica.musica_bbdd;

import com.proyectomusica.musica_bbdd.model.Artista;
import com.proyectomusica.musica_bbdd.model.ArtistaDAO;
import com.proyectomusica.musica_bbdd.model.Cancion;
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
        
        List<Lista> listas = ListaDAO.selectAll();
        
        System.out.println(listas.get(0).toStringWithCanciones());
        
        listas.get(0).getCanciones();
        
        System.out.println(listas.get(0).toStringWithCanciones());
        
        
        
        //principal();

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

    public static boolean Iniciar_sesion() {

        boolean result = false;

        System.out.println("\n+-------------------+");
        System.out.println("|   Iniciar Sesion  |");
        System.out.println("+-------------------+");
        String usuario = devolverString("Introduce tu nombre: ");
        String contrasena = devolverString("Introduce tu contraseña: ");

        List<Usuario> Buscar = UsuarioDAO.selectAll(usuario);
        if (usuario != null && contrasena != null) {
        }
        boolean bandera = false;

        for (int i = 0; i < Buscar.size() && !bandera; i++) {
            System.out.println(Buscar.get(i));
            if (Buscar.get(i).equals(usuario)) {
                System.out.println("Encontrado");
                bandera = true;

            }
        }

        return result;
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
            UsuarioDAO usuario = new UsuarioDAO(nombre, correo, foto);
            usuario.save();
            System.out.println("Usuario creado");
        }
        pulsarEnter();
    }

    static void lista_sesion(String usuario) {
        if (usuario.equals("Admin")) {
            int opcion = 0;
            do {
                System.out.println("\n+-------------------------+");
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
            System.out.println("\n+-------------------------+");
            System.out.println("|    Menu Usuario           |");
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

    static void lista_discos() {
        int opcion = 0;
        do {
            System.out.println("\n+------------------------------------+");
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

                    break;

                case 2:
                    String pattern = devolverString("Introduce el nombre del disco: ");
                    List<Disco> disco_nombre = DiscoDAO.selectAll(pattern);
                    if (disco_nombre.size() == 0) {
                        System.out.println("No se han encontrado discos");
                    } else {
                        System.out.println(disco_nombre);
                    }
                    break;

                case 3:
                    List<Disco> discos = DiscoDAO.selectAll();
                    System.out.println(discos.toString());
                    pulsarEnter();
                    break;

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
