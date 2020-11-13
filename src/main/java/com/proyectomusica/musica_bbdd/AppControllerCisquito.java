package com.proyectomusica.musica_bbdd;

import com.proyectomusica.musica_bbdd.model.Lista;
import com.proyectomusica.musica_bbdd.model.ListaDAO;
import com.proyectomusica.musica_bbdd.model.SuscripcionDAO;
import com.proyectomusica.musica_bbdd.model.Usuario;
import com.proyectomusica.musica_bbdd.utils.Utils;
import java.util.List;
import static com.proyectomusica.musica_bbdd.utils.Utils.devolverInt;
import static com.proyectomusica.musica_bbdd.utils.Utils.pulsarEnter;


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
                    //menu_suscripciones();
                    break;

            }
        } while (opcion != 0);
    }
    
    public static void menu_suscripciones(Usuario usuario){
        int opcion = 0;
        do{
            System.out.println("\n+---------------------------------+");
            System.out.println("|       Menu Suscripciones        |");
            System.out.println("+---------------------------------+");
            System.out.println("| 1) Listar suscripciones         |");
            System.out.println("| 2) Suscribirse a una lista      |");
            System.out.println("| 3) Desuscribirse de una lista   |");
            System.out.println("| 0) Salir                        |");
            System.out.println("+---------------------------------+");

            opcion = devolverInt("Introduce una opcion: ");

            switch (opcion) {
                case 1:
                    System.out.println("Tus suscripciones son: ");
                    for(Lista l: usuario.getListas_suscrito()){
                        System.out.println(l);
                    }
                    pulsarEnter();
                    break;

                case 2:
                    System.out.println("Estas son las listas de canciones que hay: ");
                    List<Lista> all_listas = ListaDAO.selectAll();
                    for(int i=0; i<all_listas.size(); i++){
                        System.out.println(i+1 + ".- " + all_listas.get(i));
                    }
                    
                    int n_lista = devolverInt("Introduzca el numero de la lista para suscribirse o 0 para salir: ");
                    
                    if(n_lista!=0 && n_lista>0 && n_lista<=all_listas.size()){
                        Lista listaSelec = all_listas.get(n_lista-1);
                        
                        if(!UsuarioEnLista(listaSelec.getUsuarios_suscritos(), usuario)){
                            SuscripcionDAO.guardarSuscripcion(usuario.getId(), listaSelec.getId());
                            usuario.getListas_suscrito().add(listaSelec);
                            System.out.println("Usted se ha suscrito con exito");
                            pulsarEnter();
                        }else{
                            System.out.println("Usted ya esta suscrito a esa lista");
                            pulsarEnter();
                        }
                    }else if(n_lista<0 || n_lista>all_listas.size()){
                        System.out.println("Introduzca un numero correcto");
                        pulsarEnter();
                    }
                    
                    break;

                case 3:
                    List<Lista> all_listas_Suscrito = usuario.getListas_suscrito();
                    System.out.println("A que lista te quieres desuscribir: ");
                    for(int i=0; i<all_listas_Suscrito.size(); i++){
                        System.out.println(i+1 + ".- " + all_listas_Suscrito.get(i));
                    }
                    int n_listaBorrar = devolverInt("Introduzca el numero de la lista para desuscribirse o 0 para salir: ");
                    
                    if(n_listaBorrar<0 || n_listaBorrar>all_listas_Suscrito.size()){
                        System.out.println("Introduzca un numero correcto");
                        pulsarEnter();
                    }else if(n_listaBorrar!=0 && n_listaBorrar>0 && n_listaBorrar<=all_listas_Suscrito.size()){
                        Lista listaBorrar = all_listas_Suscrito.get(n_listaBorrar-1);
                        SuscripcionDAO.remove(usuario.getId(), listaBorrar.getId());
                        usuario.getListas_suscrito().remove(listaBorrar);
                        System.out.println("Usted se ha desuscrito con exito");
                        pulsarEnter();
                    }
                    
                    break;

            }
        } while (opcion != 0);
    }
    
    public static boolean UsuarioEnLista(List<Usuario> ListaEnUsuarios, Usuario usuario){
        boolean aux = false;
        for(int i=0; i<ListaEnUsuarios.size() && !aux; i++){
            if(ListaEnUsuarios.get(i).getId() == usuario.getId()){
                aux = true;
            }
        }
        return aux;
    }

}

