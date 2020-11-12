package com.proyectomusica.musica_bbdd.model;

import com.proyectomusica.musica_bbdd.utils.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaDAO extends Lista{
    private boolean persist;

    public ListaDAO() {
        super();
        persist = false;
    }

    public ListaDAO(int id, String nombre, String descripcion, Usuario creador, List<Cancion> canciones, List<Usuario> usuarios_suscritos) {
        super(id, nombre, descripcion, creador, canciones, usuarios_suscritos);
        persist = false;
    }

    public ListaDAO(String nombre, String descripcion, Usuario creador, List<Cancion> canciones, List<Usuario> usuarios_suscritos) {
        super(-1, nombre, descripcion, creador, canciones, usuarios_suscritos);
        persist = false;
    }

    public ListaDAO(Lista l) {
        this.id = l.id;
        this.nombre = l.nombre;
        this.descripcion = l.descripcion;
        this.creador = l.creador;
        this.canciones = l.canciones;
    }

    public void persist() {
        persist = true;
    }

    public void detatch() {
        persist = false;
    }

    @Override
    public void setId(int id) {
        super.setId(id);
        if (persist) {
            save();
        }
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
        if (persist) {
            save();
        }
    }

    @Override
    public void setDescripcion(String descripcion) {
        super.setDescripcion(descripcion);
        if (persist) {
            save();
        }
    }

    @Override
    public void setCreador(Usuario creador) {
        super.setCreador(creador);
        if (persist) {
            save();
        }
    }

    @Override
    public void setCanciones(List<Cancion> canciones) {
        super.setCanciones(canciones);
        if(persist){
            save();
        }
    }

    @Override
    public void setUsuarios_suscritos(List<Usuario> usuarios_suscritos) {
        super.setUsuarios_suscritos(usuarios_suscritos);
        if(persist){
            save();
        }
    }
    
    public int save(){
        int result = -1;
        
        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            
            if(this.id>0){
                //UPDATE
                String q = "UPDATE lista SET nombre = ?, descripcion = ?, id_usuario = ? WHERE id = ?";
                PreparedStatement ps = csql.prepareStatement(q);
                ps.setString(1, nombre);
                ps.setString(2, descripcion);
                ps.setInt(3, creador.id);
                ps.setInt(4, id);
                result= ps.executeUpdate();
            }else{
                //INSERT
                String q = "INSERT INTO lista (id,nombre,descripcion,id_usuario) VALUES(NULL,?,?,?)";
                PreparedStatement ps = csql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, nombre);
                ps.setString(2, descripcion);
                ps.setInt(3, creador.id);
                result = ps.executeUpdate();
                try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        result = generatedKeys.getInt(1); //devuelve el ultimo id insertado
                    }
                }
                this.id = result;
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public static List<Lista> selectAll(){
        return selectAll("");
    }
    
    /**
     * Funcion que selecciona por nombre todos los usuarios de la base de datos que sea por el pattern
     * @param pattern Palabra por lo que se filtra el select
     * @return devuelve una lista de usuarios
     */
    public static List<Lista> selectAll(String pattern){
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
                    l.setCreador(new Usuario(rs.getInt("id_usuario"), "", "", "", null, null));
                    l.setCanciones(null);
                    l.setUsuarios_suscritos(null);
                    result.add(l);
                }
            }
        }catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public static List<Lista> selectAll(int id_usuario){
        List<Lista> result = new ArrayList<>();
        
        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM lista WHERE id_usuario = ?";
            
            PreparedStatement ps = csql.prepareStatement(q);
            
            ps.setInt(1, id_usuario);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Lista l = new Lista();
                    l.setId(rs.getInt("id"));
                    l.setNombre(rs.getString("nombre"));
                    l.setDescripcion(rs.getString("descripcion"));
                    l.setCreador(new Usuario(rs.getInt("id_usuario"), "", "", "", null, null));
                    l.setCanciones(null);
                    l.setUsuarios_suscritos(null);
                    result.add(l);
                }
            }
        }catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ListaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public int remove(){
        int result = -1;
        
        if(this.id > 0){
            
            try{
                java.sql.Connection csql = ConnectionUtil.getConnection();
                String q = "DELETE FROM usuario WHERE id = " + this.id;
                PreparedStatement ps = csql.prepareStatement(q);
                result = ps.executeUpdate();
                if(result>0)
                    this.id=-1;
            }catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }
}
