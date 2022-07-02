/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saivent.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Elliot
 */
public class ConexionJDBC {
    
     Connection connection = null;
     public Connection connectDatabase() {
        String url = "";
        String host="192.168.99.201";
        String database = "mitras31oct21_wilmer";
        try {
            // We register the PostgreSQL driver
            // Registramos el driver de PostgresSQL
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            url = "jdbc:postgresql://" + host + ":5432/" + database;
            // Database connect
            // Conectamos con la base de datos
            connection = DriverManager.getConnection(
                    url,
                    "saicoop","slufpana?");           
            boolean valid = connection.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
        } catch (java.sql.SQLException sqle) { 
            System.out.println("Error al conectar con la base de datos de PostgreSQL (" + url + "): " + sqle);
        }
      return connection;
     }
     public Connection getConnection(){
        return connection;
     }
}
