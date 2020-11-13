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

public class ArtistaDAO extends Artista {

    private boolean persist;

    public ArtistaDAO() {
        super();
        persist = false;
    }

    public ArtistaDAO(int id, String nombre, String nacionalidad, String foto, List<Disco> disco) {
        super(id, nombre, nacionalidad, foto, disco);
        persist = false;
    }

    public ArtistaDAO(String nombre, String nacionalidad, String foto, List<Disco> disco) {
        super(-1, nombre, nacionalidad, foto, disco);
        persist = false;
    }

    public ArtistaDAO(Artista a) {
        this.setId(a.getId());
        this.setNombre(a.getNombre());
        this.setNacionalidad(a.getNacionalidad());
        this.setFoto(a.getFoto());
        this.setDisco(a.getDisco());
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
    public void setNacionalidad(String nacionalidad) {
        super.setNacionalidad(nacionalidad);
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

    @Override
    public void setDisco(List<Disco> Disco) {
        super.setDisco(disco);
        if (persist) {
            save();
        }
    }

    /**
     * Metodo que guarda o edita un artista
     * @return -1 en caso de que no haga nada o el id del artista que hayamos agregado o editado
     */
    public int save() {
        int result = -1;

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();

            if (this.getId() > 0) {
                //UPDATE
                String q = "UPDATE artista SET nombre = ?, nacionalidad = ?, foto = ? WHERE id = ?";
                PreparedStatement ps = csql.prepareStatement(q);
                ps.setString(1, this.getNombre());
                ps.setString(2, this.getNacionalidad());
                ps.setString(3, this.getFoto());
                ps.setInt(4, this.getId());
                result = ps.executeUpdate();
            } else {
                //INSERT
                String q = "INSERT INTO artista (id,nombre,nacionalidad,foto) VALUES(NULL,?,?,?)";
                PreparedStatement ps = csql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, this.getNombre());
                ps.setString(2, this.getNacionalidad());
                ps.setString(3, this.getFoto());
                result = ps.executeUpdate();
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1); //devuelve el ultimo id insertado
                    }
                }
                this.setId(result);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * Metodo que devuelve todos los artistas de la base de datos
     * @return todos los artistas
     */
    public static List<Artista> selectAll() {
        return selectAll("");
    }

    /**
     * Metodo que selecciona a todos los artistas que contengan el nombre pasado
     * @param pattern nombre que contenga el artista
     * @return la lista de todos los artistas que contengan el nombre de pattern
     */
    public static List<Artista> selectAll(String pattern) {
        List<Artista> result = new ArrayList<>();

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM artista";

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
                    Artista a = new Artista();
                    a.setId(rs.getInt("id"));
                    a.setNombre(rs.getString("nombre"));
                    a.setNacionalidad(rs.getString("nacionalidad"));
                    a.setFoto(rs.getString("foto"));
                    //a.setDisco(new Disco(rs.getInt("id"), "", "", null, null));
                    result.add(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * Metodo que devuelve el artista que tiene el id pasado
     * @param id el id del artista en cuestion
     * @return el artista con ese id
     */
    public static Artista selectAllForId(int id) {
        Artista result = new Artista();
        
        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM artista WHERE id = ?";
            
            PreparedStatement ps = csql.prepareStatement(q);
            
            ps.setInt(1,id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next() != false){
                result.id= rs.getInt("id");
                result.nombre = rs.getString("nombre");
                result.nacionalidad = rs.getString("nacionalidad");
                result.foto = rs.getString("foto");
            }
        }catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    /**
     * Metodo que devuelve el artista que tiene exactamente el nombre que se pasa
     * @param nombre el nombre en cuestion del artista
     * @return el artista con ese nombre
     */
    public static Artista selectAllForNombre(String nombre) {
        Artista result = null;

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM artista WHERE nombre=?";
            
            PreparedStatement ps = csql.prepareStatement(q);

            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            if (rs.next() != false) {
                result = new Artista();
                result.setId(rs.getInt("id"));
                result.setNombre(rs.getString("nombre"));
                result.setNacionalidad(rs.getString("nacionalidad"));
                result.setFoto(rs.getString("foto"));
                //a.setDisco(new Disco(rs.getInt("id"), "", "", null, null));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * Borra de la base de datos el artista
     * @return -1 si no se ha borrado o el id del artista borrado
     */
    public int remove() {
        int result = -1;

        if (this.getId() > 0) {

            try {
                java.sql.Connection csql = ConnectionUtil.getConnection();
                String q = "DELETE FROM artista WHERE id = " + this.getId();
                PreparedStatement ps = csql.prepareStatement(q);
                result = ps.executeUpdate();
                if (result > 0) {
                    this.setId(-1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return result;
    }
}
