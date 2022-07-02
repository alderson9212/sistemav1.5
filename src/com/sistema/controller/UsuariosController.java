/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.controller;

import com.sistema.modelo.ProductoDTO;
import com.sistema.modelo.UnidadesMedidaDTO;
import com.sistema.modelo.UsuarioDTO;
import com.sistema.util.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wilmer
 */
public class UsuariosController {
       
    conexion con = new conexion();
    Statement stmt;
    ResultSet rs;
    String sql = "";
    Connection connect = null;
    PreparedStatement ps;

    public List<UsuarioDTO> usuariosAll(String nombre) {
        List<UsuarioDTO> users = new ArrayList<>();
        try {
            connect = con.connectDatabase();
            if(!"".equals(nombre)){
                sql = "SELECT * FROM usuarios WHERE nombre='"+nombre+"'";
            }else{
                sql = "SELECT * FROM usuarios";
            }            
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);            
            while (rs.next()) {
                UsuarioDTO user = new UsuarioDTO();
                user.setId(rs.getInt(1));
                user.setNombre(rs.getString(2));
                user.setPassword(rs.getString(3)); 
                user.setCorreo(rs.getString(4));
                user.setRol(rs.getString(5));
                users.add(user);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener usuario por nombre:" + e.getMessage());
        }
        return users;
    }
    
    
    public boolean save(UsuarioDTO usuario) {
        boolean bandera = false;
        connect = con.connectDatabase();        
        try {
            sql = "INSERT INTO usuarios(nombre,pass,correo,rol) VALUES(?,?,?,?)";
            ps = connect.prepareStatement(sql);
            ps.setString(1, usuario.getNombre().trim());
            ps.setString(2, usuario.getPassword().trim());
            ps.setString(3, usuario.getCorreo().trim());            
            ps.setString(4, usuario.getRol());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al insertar usuario:" + e.getMessage());
        }
        return bandera;
    }

    public boolean update(UsuarioDTO usuario) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "UPDATE productos set nombre=?,correo=?,contraseña=?,rol=? WHERE id=?";
            ps = connect.prepareStatement(sql);
            ps.setInt(5,usuario.getId());
            ps.setString(1, usuario.getNombre().trim());
            ps.setString(2, usuario.getCorreo().trim());
            ps.setString(3, usuario.getPassword().trim());
            ps.setString(4, usuario.getRol());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al modificar usuario:" + e.getMessage());
        }
        return bandera;
    }
    
     public boolean detelete(int id) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "DELETE FROM usuarios WHERE id=?";
            ps = connect.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al eliminar usuario:" + e.getMessage());
        }
        return bandera;
    }
    
}
