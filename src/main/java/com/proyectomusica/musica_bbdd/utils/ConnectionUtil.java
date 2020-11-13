package com.proyectomusica.musica_bbdd.utils;

import com.proyectomusica.musica_bbdd.model.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionUtil {
    private static java.sql.Connection _conn = null;
    
    /**
     * Metodo que conecta a la base de datos, a traves del driver
     * @param c es un archivo xml que contiene los datos de nuestra base de datos
     * @return la conexion a la base de datos
     * @throws ClassNotFoundException las clase "com.mysql.cj.jdbc.Driver" no se encuentra en la ruta de clases
     * @throws SQLException Falta la base de datos
     */
    public static java.sql.Connection connect(Connection c) throws ClassNotFoundException, SQLException{
        java.sql.Connection conn = null;
        
        if(c==null){
            conn = null;
        }else{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://"+c.getHost()+"/"+c.getDb()+
                    "?useLegacyDatetimeCode=false&serverTimezone=UTC",c.getUser(),c.getPassword());
        }
        
        return conn; 
    }
    
    /**
     * Metodo que nos devuelve la conexion a la base de datos
     * @return la conexion a la base de datos
     */
    public static java.sql.Connection getConnection(){
        if(_conn == null){
            Connection c = new Connection();
            c.loadDataXML();
            try {
                _conn=connect(c);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                System.out.println("Falta la base de datos");
                System.exit(0);
                Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return _conn;
    }
    
    /**
     * Metodo que cierra la conexion a la base de datos
     */
    public static void closeConnection(){
        if(_conn!=null){
            try {
                _conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
