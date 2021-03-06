package com.proyectomusica.musica_bbdd.model;

import com.proyectomusica.musica_bbdd.utils.ConnectionUtil;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiscoDAO extends Disco {

    private boolean persist;

    public DiscoDAO() {
        super();
        persist = false;
    }

    public DiscoDAO(int id, String nombre, String foto, Artista creador, Date fecha_produccion, List<Cancion> canciones) {
        super(id, nombre, foto, creador, fecha_produccion, canciones);
        persist = false;
    }

    public DiscoDAO(String nombre, String foto, Date fecha_produccion, Artista creador, List<Cancion> canciones) {
        super(-1, nombre, foto, creador, fecha_produccion, canciones);
        persist = false;
    }

    public DiscoDAO(Disco d) {
        this.id = d.id;
        this.nombre = d.nombre;
        this.foto = d.foto;
        this.creador = d.creador;
        this.fecha_produccion = d.fecha_produccion;
        this.canciones = d.canciones;    
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
    public void setFoto(String foto) {
        super.setFoto(foto);
        if (persist) {
            save();
        }
    }

    public void setFecha_Produccion(Date fecha_produccion) {
        super.setFecha_produccion(fecha_produccion);
        if (persist) {
            save();
        }
    }

    public void setCreador(Artista creador) {
        super.setCreador(creador);
        if (persist) {
            save();
        }
    }
    
    public void setCanciones(List<Cancion> canciones){
        super.setCanciones(canciones);
        if(persist){
            save();
        }
    }
    
    /**
     * Metodo que guarda o edita un disco
     * @return -1 en caso de que no haga nada o el id del disco que hayamos agregado o editado
     */
    public int save() {
        int result = -1;

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();

            if (this.getId() > 0) {
                //UPDATE
                String q = "UPDATE disco SET nombre = ?, foto = ? , id_artista=?, fecha_pro=? WHERE id = ?";
                PreparedStatement ps = csql.prepareStatement(q);
                ps.setString(1, this.getNombre());
                ps.setString(2, this.getFoto());
                ps.setInt(3, this.creador.id);
                ps.setDate(4, this.getFecha_produccion());
                ps.setInt(5, this.getId());
                result = ps.executeUpdate();
            } else {
                //INSERT
                String q = "INSERT INTO disco (id,nombre,foto,id_artista,fecha_pro) VALUES(NULL,?,?,?,?)";
                PreparedStatement ps = csql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, this.getNombre());
                ps.setString(2, this.getFoto());
                ps.setObject(3, this.creador.id);
                ps.setDate(4, this.getFecha_produccion());

                result = ps.executeUpdate();
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1); //devuelve el ultimo id insertado
                    }
                }
                this.setId(result);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    /**
     * Metodo que devuelve todos los discos de la base de datos
     * @return todos los artistas
     */
    public static List<Disco> selectAll() {
        return selectAll("");
    }

    /**
     * Metodo que selecciona a todos los discos que contengan el nombre pasado
     * @param pattern nombre que contenga el disco
     * @return la lista de todos los discos que contengan el nombre de pattern
     */
    public static List<Disco> selectAll(String pattern) {
        List<Disco> result = new ArrayList<>();

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM disco";

            if (pattern.length() > 0) {
                q += " WHERE nombre LIKE ?";
            }

            PreparedStatement ps = csql.prepareStatement(q);

            if (pattern.length() > 0) {
                ps.setString(1, pattern + "%");
            }

            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Disco d = new Disco();
                    d.setId(rs.getInt("id"));
                    d.setNombre(rs.getString("nombre"));
                    d.setFoto(rs.getString("foto"));
                    d.setCreador(new Artista(rs.getInt("id_artista"), "", "", "", null));
                    d.setFecha_produccion(rs.getDate("fecha_pro"));
                    d.setCanciones(null);
                    
                    result.add(d);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    /**
     * Metodo que devuelve el disco que tiene el id pasado
     * @param id el id del disco en cuestion
     * @return el disco con ese id
     */
    public static Disco selectAllForId(int id) {
        Disco result = null;

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM disco WHERE id=?";
            
            PreparedStatement ps = csql.prepareStatement(q);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next() != false) {
                result = new Disco();
                result.setId(rs.getInt("id"));
                result.setNombre(rs.getString("nombre"));
                result.setFoto(rs.getString("foto"));
                result.setCreador(new Artista(rs.getInt("id_artista"), "", "", "", null));
                result.setFecha_produccion(rs.getDate("fecha_pro"));
                result.setCanciones(null);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    /**
     * Metodo que devuelve el disco que tiene exactamente el nombre que se pasa
     * @param Nombre el nombre en cuestion del disco
     * @return el disco con ese nombre
     */
    public static Disco selectAllForNombre(String Nombre) {
        Disco result = null;

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM disco WHERE nombre=?";
            
            PreparedStatement ps = csql.prepareStatement(q);
            ps.setString(1, Nombre);

            ResultSet rs = ps.executeQuery();

            if (rs.next() != false) {
                result = new Disco();
                result.setId(rs.getInt("id"));
                result.setNombre(rs.getString("nombre"));
                result.setFoto(rs.getString("foto"));
                result.setCreador(new Artista(rs.getInt("id_artista"), "", "", "", null));
                result.setFecha_produccion(rs.getDate("fecha_pro"));
                result.setCanciones(null);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    /**
     * Metodo que devuelve los discos que tiene el id de artista pasado
     * @param id_artista el id del artista en cuestion
     * @return lista de discos que tienen el id_artista seleccionado
     */
    public static List<Disco> selectAll(int id_artista) {
        List<Disco> result = new ArrayList<>();

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM disco WHERE id_artista=?";
            
            PreparedStatement ps = csql.prepareStatement(q);
            ps.setInt(1, id_artista);

            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Disco d = new Disco();
                    d.setId(rs.getInt("id"));
                    d.setNombre(rs.getString("nombre"));
                    d.setFoto(rs.getString("foto"));
                    d.setCreador(new Artista(rs.getInt("id_artista"), "", "", "", null));
                    d.setFecha_produccion(rs.getDate("fecha_pro"));
                    d.setCanciones(null);
                    
                    result.add(d);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    /**
     * Borra de la base de datos el disco
     * @return -1 si no se ha borrado o el id del disco borrado
     */
    public int remove() {
        int result = -1;

        if (this.getId() > 0) {

            try {
                java.sql.Connection csql = ConnectionUtil.getConnection();
                String q = "DELETE FROM disco WHERE id = " + this.getId();
                PreparedStatement ps = csql.prepareStatement(q);
                result = ps.executeUpdate();
                if (result > 0) {
                    this.setId(-1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

}
