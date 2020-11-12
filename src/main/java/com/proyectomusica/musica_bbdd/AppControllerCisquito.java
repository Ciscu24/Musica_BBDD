package com.proyectomusica.musica_bbdd;

import static com.proyectomusica.musica_bbdd.utils.Utils.devolverInt;

public class AppControllerCisquito {
    public static void menu_clientes(){
        int opcion = 0;
        do{
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
                    //lista_discos();
                    break;

                case 2:
                    break;

                case 3:
                    menu_suscripciones();
                    break;

            }
        } while (opcion != 0);
    }
    
    public static void menu_suscripciones(){
        
    }

}
