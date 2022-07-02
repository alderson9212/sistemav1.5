/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilmer
 */
public class conexion {

    public Connection connectDatabase() {
        String user = "root";
        String pass = "root";
        String url = "jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8";
        //String url = "jdbc:mysql://localhost:3306/gateway?autoReconnect=true&amp;failOverReadOnly=false&amp;maxReconnects=10&amp;removeAbandonedTimeout=60&amp;testWhileIdle=true&amp;timeBetweenEvictionRunsMillis=300000";
        Connection con = null;

        try {
           Class.forName("com.mysql.jdbc.Driver"); 
           con = DriverManager.getConnection(url, user, pass);

        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
