package com.proyectomusica.musica_bbdd.model;

import com.proyectomusica.musica_bbdd.utils.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuscripcionDAO {
    
     /**
     * Funcion que selecciona por id del usuario la subcripciones de la base de datos
     *
     * @param id_usuario id por lo que se filtra el select
     * @return devuelve una lista de subcripciones del usuario
     */
    public static List<Lista> selectAllListas(int id_usuario) {
        List<Lista> result = new ArrayList<>();

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM lista AS l JOIN suscripcion AS s ON s.id_lista=l.id WHERE s.id_usuario = ?";

            PreparedStatement ps = csql.prepareStatement(q);

            ps.setInt(1, id_usuario);

            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Lista l=  new Lista();
                    l.setId(rs.getInt("id"));
                    l.setNombre(rs.getString("nombre"));
                    l.setDescripcion(rs.getString("descripcion"));
                    l.setCreador(new Usuario(rs.getInt("id_usuario"), "", "", "", null, null));
                    l.setCanciones(null);
                    result.add(l);
                } 
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
     /**
     * Funcion que selecciona todos los usuarios que tiene una lista
     *
     * @param id_lista id por lo que se filtra el select
     * @return devuelve una lista de usuario
     */
    public static List<Usuario> selectAllUsuario(int id_lista) {
        List<Usuario> result = new ArrayList<>();

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM usuario AS u JOIN suscripcion AS s ON s.id_usuario=u.id WHERE s.id_lista = ?";

            PreparedStatement ps = csql.prepareStatement(q);

            ps.setInt(1, id_lista);

            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Usuario u=  new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNombre(rs.getString("nombre"));
                    u.setCorreo(rs.getString("correo"));
                    u.setFoto(rs.getString("foto"));
                    u.setListas_creadas(null);
                    u.setListas_suscrito(null);
                    result.add(u);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    
     /**
     * Funcion que guarda la subcripcion en la base de datos
     *
     * @param id_usuario,id_lista id por lo que se filtra el select
     */
    public static void guardarSuscripcion(int id_usuario, int id_lista){        
        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            
            String q = "INSERT INTO suscripcion (id_usuario, id_lista) VALUES (?,?)";
            PreparedStatement ps = csql.prepareStatement(q);
            ps.setInt(1, id_usuario);
            ps.setInt(2, id_lista);
            ps.executeUpdate();
         
        }catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     /**
     * Borra de la base de datos la subcripcion de un usuario
     *
     * @return -1 si no se ha borrado o el id de la lista de reproduccion borrada
     */
    
    public static void remove(int id_usuario, int id_lista){   
        try{
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "DELETE FROM suscripcion WHERE id_usuario = ? and id_lista = ?";
            PreparedStatement ps = csql.prepareStatement(q);
            ps.setInt(1, id_usuario);
            ps.setInt(2, id_lista);
            ps.executeUpdate();
        }catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
