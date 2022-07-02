/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.controller;

import com.sistema.modelo.Config;
import com.sistema.util.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author wilmer
 */
public class NegocioController {

    conexion con = new conexion();
    Connection connect = null;
    Statement stmt;
    ResultSet rs;
    String sql = "";
    PreparedStatement ps;

    public Config busquedaDatos() {
        Config config = new Config();
        connect = con.connectDatabase();
        try {
            sql = "SELECT * from empresa";
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
               config.setRuc(rs.getString(1));
               config.setNombre(rs.getString(2));
               config.setDireccion(rs.getString(3));
               config.setTelefono(rs.getString(4));
               config.setMensaje(rs.getString(5));
            }
            connect.close();
        } catch (Exception ex) {
            System.out.println("Error al obtener nombre negocio:" + ex.getMessage());
        }
        return config;
    }

    public boolean save(Config config) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "INSERT INTO empresa VALUES(?,?,?,?,?)";
            ps = connect.prepareStatement(sql);
            ps.setString(1, config.getRuc());
            ps.setString(2, config.getNombre());
            ps.setString(3, config.getTelefono());
            ps.setString(4, config.getDireccion());
            ps.setString(5, config.getMensaje());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al insertar nombre negocio:" + e.getMessage());
        }
        return bandera;
    }
    
     public boolean delete() {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "DELETE FROM empresa";
            ps = connect.prepareStatement(sql);
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al insertar eliminar datos empresa:" + e.getMessage());
        }
        return bandera;
    }
}
