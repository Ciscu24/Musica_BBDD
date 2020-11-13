package com.proyectomusica.musica_bbdd.model;

import com.proyectomusica.musica_bbdd.utils.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CancionDAO extends Cancion{
    private boolean persist;

    public CancionDAO() {
        super();
        persist = false;
    }
    

    public CancionDAO(int id, String nombre, int duracion, Disco disco_contenedor, List<Lista> listas) {
        super(id, nombre, duracion, disco_contenedor, listas);
        persist = false;
    }

    public CancionDAO(String nombre, int duracion, Disco disco_contenedor, List<Lista> listas) {
        super(-1, nombre, duracion, disco_contenedor, listas);
        persist = false;
    }

    public CancionDAO(Cancion c) {
        this.id = c.id;
        this.nombre = c.nombre;
        this.duracion = c.duracion;
        this.disco_contenedor = c.disco_contenedor;
        this.listas = c.listas;
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
    public void setDuracion(int duracion) {
        super.setDuracion(duracion);
        if (persist) {
            save();
        }
    }

    public void setDisco_contenedor(Disco disco_contenedor){
        super.setDisco_contenedor(disco_contenedor);
        if (persist) {
            save();
        }
    }

    @Override
    public void setListas(List<Lista> listas) {
        super.setListas(listas);
        if (persist) {
            save();
        }
    }

    /**
     * Metodo que guarda o edita una cancion
     * @return -1 en caso de que no haga nada o el id de la cancion que hayamos agregado o editado
     */
    public int save() {
        int result = -1;

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();

            if (this.getId() > 0) {
                //UPDATE
                String q = "UPDATE cancion SET nombre = ?, duracion = ?, id_disco=? WHERE id = ?";
                PreparedStatement ps = csql.prepareStatement(q);
                ps.setString(1, this.getNombre());
                ps.setInt(2, this.getDuracion());
                ps.setInt(3, this.disco_contenedor.id);
                ps.setInt(4, this.id);
                result = ps.executeUpdate();
            } else {
                //INSERT
                String q = "INSERT INTO cancion (id,nombre,duracion,id_disco) VALUES(NULL,?,?,?)";
                PreparedStatement ps = csql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, this.getNombre());
                ps.setInt(2, this.duracion);
                ps.setInt(3, this.disco_contenedor.id);
                result = ps.executeUpdate();
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        result = generatedKeys.getInt(1); //devuelve el ultimo id insertado
                    }
                }
                this.setId(result);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CancionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    /**
     * Metodo que devuelve todas las canciones de la base de datos
     * @return todas las canciones
     */
    public static List<Cancion> selectAll() {
        return selectAll("");
    }

    /**
     * Metodo que selecciona a todas las canciones que contengan el nombre pasado
     * @param pattern nombre que contenga la cancion
     * @return la lista de todas las canciones que contengan el nombre de pattern
     */
    public static List<Cancion> selectAll(String pattern) {
        List<Cancion> result = new ArrayList<>();

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM cancion";

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
                    Cancion c = new Cancion();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setDuracion(rs.getInt("duracion"));
                    c.setDisco_contenedor(new Disco(rs.getInt("id_disco")));
                    c.setListas(null);
                    result.add(c);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    /**
     * Metodo que devuelve todas las canciones que tengan el id_disco pasado
     * @param id_disco el id del disco que contienen las canciones
     * @return todas la canciones que estan dentro del id_disco pasado
     */
    public static List<Cancion> selectAll(int id_disco) {
        List<Cancion> result = new ArrayList<>();

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM cancion WHERE id_disco = ?";

            PreparedStatement ps = csql.prepareStatement(q);

            ps.setInt(1, id_disco);

            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Cancion c = new Cancion();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setDuracion(rs.getInt("duracion"));
                    c.setDisco_contenedor(new Disco(rs.getInt("id_disco")));
                    c.setListas(null);
                    result.add(c);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    /**
     * Metodo que devuelve la cancion que tiene el id pasado
     * @param id el id de la cancion en cuestion
     * @return la cancion con ese id
     */
    public static Cancion selectAllForId(int id) {
        Cancion result = null;

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM cancion WHERE id = ?";

            PreparedStatement ps = csql.prepareStatement(q);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    result = new Cancion();
                    result.setId(rs.getInt("id"));
                    result.setNombre(rs.getString("nombre"));
                    result.setDuracion(rs.getInt("duracion"));
                    result.setDisco_contenedor(new Disco(rs.getInt("id_disco")));
                    result.setListas(null);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * Borra de la base de datos la cancion
     * @return -1 si no se ha borrado o el id de la cancion borrada
     */
    public int remove() {
        int result = -1;

        if (this.getId() > 0) {

            try {
                java.sql.Connection csql = ConnectionUtil.getConnection();
                String q = "DELETE FROM cancion WHERE id = " + this.getId();
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
