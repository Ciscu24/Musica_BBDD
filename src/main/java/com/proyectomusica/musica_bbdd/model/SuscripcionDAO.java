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
}
