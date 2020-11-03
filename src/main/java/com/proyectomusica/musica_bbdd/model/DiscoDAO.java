/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author matad
 */
public class DiscoDAO extends Disco {

    private boolean persist;

    public DiscoDAO() {
        super();
        persist = false;
    }

    public DiscoDAO(int id, String nombre, String foto,
            Timestamp fecha_produccion, Artista[] artistas) {
        super(id, nombre, foto, fecha_produccion, artistas);
        persist = false;
    }

    public DiscoDAO(String nombre, String foto,
            Timestamp fecha_produccion, Artista[] artistas) {
        super(-1, nombre, foto, fecha_produccion, artistas);
        persist = false;
    }

    public DiscoDAO(Disco d) {
        this.setId(d.getId());
        this.setNombre(d.getNombre());
        this.setFoto(d.getFoto());
        this.setFecha_produccion(d.getFecha_produccion());
        this.setArtistas(d.getArtistas());

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

    
    public void setFecha_Produccion(Timestamp fecha_produccion) {
        super.setFecha_produccion(fecha_produccion);
        if (persist) {
            save();
        }
    }

    public void setArtista(Artista[] artistas) {
        super.setArtistas(artistas);
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
                String q = "UPDATE disco SET nombre = ?, foto = ? , fecha_produccion=?, artistas=? WHERE id = ?";
                PreparedStatement ps = csql.prepareStatement(q);
                ps.setString(1, this.getNombre());
                ps.setString(2, this.getFoto());
                ps.setTimestamp(3, this.getFecha_produccion());
                ps.setObject(4, this.getArtistas());
                ps.setInt(5, this.getId());
                result = ps.executeUpdate();
            } else {
                //INSERT
                String q = "INSERT INTO disco (id,nombre,foto,fecha_produccion,artistas) VALUES(NULL,?,?,?,?,?)";
                PreparedStatement ps = csql.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, this.getNombre());
                ps.setString(2, this.getFoto());
                ps.setTimestamp(3, this.getFecha_produccion());
                ps.setObject(4, this.getArtistas());
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

    public static List<Disco> selectAll() {
        return selectAll("");
    }

    /**
     * Funcion que selecciona por usuario todos los clientes de la base de datos
     * que sea por el pattern
     *
     * @param pattern Palabra por lo que se filtra el select
     * @return devuelve una lista de clientes
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
                    d.setFecha_produccion(rs.getTimestamp("fecha_produccion"));
                    d.setArtistas((Artista[]) rs.getObject("artistas"));

                    result.add(d);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(DiscoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

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
