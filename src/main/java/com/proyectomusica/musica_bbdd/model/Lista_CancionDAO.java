package com.proyectomusica.musica_bbdd.model;

import com.proyectomusica.musica_bbdd.utils.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Lista_CancionDAO {

    /**
     * Funcion que selecciona por usuario todos los clientes de la base de datos
     * que sea por el pattern
     *
     * @param id_lista Palabra por lo que se filtra el select
     * @return devuelve una lista de clientes
     */
    public static List<Cancion> selectAllCanciones(int id_lista) {
        List<Cancion> result = new ArrayList<>();

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM cancion AS c JOIN lista_cancion AS lc ON c.id=lc.id_cancion WHERE lc.id_lista = ?";

            PreparedStatement ps = csql.prepareStatement(q);

            ps.setInt(1, id_lista);

            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Cancion c = new Cancion();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setDuracion(rs.getInt("duracion"));
                    result.add(c);
                } 
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static List<Lista> selectAllLista(){
        return selectAllLista("");
    }
    
    public static List<Lista> selectAllLista(String pattern){
        List<Lista> result = new ArrayList<>();
        
        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM lista";
            
            if(pattern.length()>0){
                q+=" WHERE nombre LIKE ?";
            }
            
            PreparedStatement ps = csql.prepareStatement(q);
            
            if(pattern.length()>0){
                ps.setString(1, pattern+"%");
            }
            
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Lista l = new Lista();
                    l.setId(rs.getInt("id"));
                    l.setNombre(rs.getString("nombre"));
                    l.setDescripcion(rs.getString("descripcion"));
                    l.setCreador(new Usuario(rs.getInt("id_usuario"), "", "", ""));
                    l.setCanciones(null);
                    result.add(l);
                }
            }
        }catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }

}
