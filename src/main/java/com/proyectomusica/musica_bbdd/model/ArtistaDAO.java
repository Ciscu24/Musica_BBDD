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

    public ArtistaDAO(int id, String nombre, String nacionalidad, String foto) {
        super(id, nombre, nacionalidad, foto);
        persist = false;
    }

    public ArtistaDAO(String nombre, String nacionalidad, String foto) {
        super(-1, nombre, nacionalidad, foto);
        persist = false;
    }

    public ArtistaDAO(Artista a) {
        this.setId(a.getId());
        this.setNombre(a.getNombre());
        this.setNacionalidad(a.getNacionalidad());
        this.setFoto(a.getFoto());
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

    public static List<Artista> selectAll() {
        return selectAll("");
    }

    /**
     * Funcion que selecciona por usuario todos los clientes de la base de datos
     * que sea por el pattern
     *
     * @param pattern Palabra por lo que se filtra el select
     * @return devuelve una lista de clientes
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
                    result.add(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ArtistaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

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
