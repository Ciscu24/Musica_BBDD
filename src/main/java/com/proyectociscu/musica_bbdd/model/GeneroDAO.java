package com.proyectociscu.musica_bbdd.model;

import com.proyectociscu.musica_bbdd.utils.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneroDAO extends Genero{
     private boolean persist;

    public GeneroDAO() {
        super();
        persist = false;
    }

    public GeneroDAO(int id, String nombre) {
        super(id, nombre);
        persist = false;
    }

    public GeneroDAO(String nombre) {
        super(-1, nombre);
    }

    public GeneroDAO(Genero g) {
        this.setId(g.getId());
        this.setNombre(g.getNombre());
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


    public int save() {
        int result = -1;

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();

            if (this.getId() > 0) {
                //UPDATE
                String q = "UPDATE genero SET nombre = ? WHERE id = ?";
                PreparedStatement ps = csql.prepareStatement(q);
                ps.setString(1, this.getNombre());
                ps.setInt(2, this.getId());
                result = ps.executeUpdate();
            } else {
                //INSERT
                String q = "INSERT INTO genero (id,nombre) VALUES(NULL,?)";
                PreparedStatement ps = csql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, this.getNombre());
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

    public static List<Genero> selectAll() {
        return selectAll("");
    }

    /**
     * Funcion que selecciona por usuario todos los clientes de la base de datos
     * que sea por el pattern
     *
     * @param pattern Palabra por lo que se filtra el select
     * @return devuelve una lista de clientes
     */
    public static List<Genero> selectAll(String pattern) {
        List<Genero> result = new ArrayList<>();

        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "SELECT * FROM genero";

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
                    Genero a = new Genero();
                    a.setId(rs.getInt("id"));
                    a.setNombre(rs.getString("nombre"));
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
                String q = "DELETE FROM genero WHERE id = " + this.getId();
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
