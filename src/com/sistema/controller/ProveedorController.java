/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.controller;

import com.sistema.modelo.ProductoDTO;
import com.sistema.modelo.ProveedorDTO;
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
public class ProveedorController {

    conexion con = new conexion();
    Connection connect = null;
    Statement stmt;
    ResultSet rs;
    String sql = "";
    PreparedStatement ps;

    public List<ProveedorDTO> proveedoresAll(String valor) {
        List<ProveedorDTO> lista = new ArrayList<>();
        connect = con.connectDatabase();
        try {
            if (!valor.equals("")) {
                System.out.println("entro aqui");
                sql = "SELECT * FROM proveedores WHERE UPPER(nombre) LIKE '%" + valor.toUpperCase() + "%'";
                System.out.println(""+sql);
            } else {
                sql = "SELECT * FROM proveedores";
            }
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProveedorDTO proveedor = new ProveedorDTO();
                proveedor.setIdproveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2).trim());
                proveedor.setTelefono(rs.getString(3));
                proveedor.setMail(rs.getString(4));                
                
                lista.add(proveedor);
            }
            connect.close();
        } catch (Exception ex) {
            System.out.println("Error al obtener lista de proveedores:" + ex.getMessage());
        }
        return lista;
    }

    public ProveedorDTO proveedorById(int id) {
        ProveedorDTO proveedor = new ProveedorDTO();
        connect = con.connectDatabase();
        try {
            sql = "SELECT * FROM proveedores WHERE idproveedor=" + id;
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                proveedor.setIdproveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2).trim());
                proveedor.setTelefono(rs.getString(3).trim());
                proveedor.setMail(rs.getString(4).trim());
            }
            connect.close();
        } catch (Exception ex) {
            System.out.println("Error al obtener proveedor:" + ex.getMessage());
        }
        return proveedor;
    }

    public ProveedorDTO proveedorByNombre(String nombre) {
        ProveedorDTO proveedor = new ProveedorDTO();
        connect = con.connectDatabase();
        try {
            sql = "SELECT * FROM proveedores WHERE nombre='" + nombre + "'";
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                proveedor.setIdproveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2).trim());
                proveedor.setTelefono(rs.getString(3).trim());
                proveedor.setMail(rs.getString(4).trim());
            }
            connect.close();
        } catch (Exception ex) {
            System.out.println("Error al obtener proveedor por nombre:" + ex.getMessage());
        }
        return proveedor;
    }

    public boolean save(ProveedorDTO proveedor) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "INSERT INTO proveedores VALUES(?,?,?,?)";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, proveedor.getIdproveedor());
            ps.setString(2, proveedor.getNombre().trim());
            ps.setString(3, String.valueOf(proveedor.getTelefono()));
            ps.setString(4, proveedor.getMail().trim());           
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al insertar proveedor:" + e.getMessage());
        }
        return bandera;
    }

    public boolean update(ProveedorDTO proveedor) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "UPDATE proveedores set nombre =?,mail=?,telefono=? WHERE idproveedor =?";
            ps = connect.prepareStatement(sql);
            ps.setString(1,proveedor.getNombre());
            ps.setString(2, proveedor.getMail());
            ps.setString(3, proveedor.getTelefono());
            ps.setInt(4, proveedor.getIdproveedor());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al modificar proveedor:" + e.getMessage());
        }
        return bandera;
    }
    
    
    public boolean delete(int id) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "DELETE FROM proveedores WHERE idproveedor =?";
            ps = connect.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al eliminar proveedor:" + e.getMessage());
        }
        return bandera;
    }

    public int generarSecuenciaId() {
        int c = 0;
        connect = con.connectDatabase();

        try {
            sql = "SELECT * FROM proveedores ORDER BY idproveedor DESC LIMIT 1";
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                c = rs.getInt(1);
            }
            if (c == 0) {
                c = 1;
            } else {
                c = c + 1;
            }
        } catch (Exception ex) {
            System.out.println("Error al generar numeracion proveedores:" + ex.getMessage());
        }
        return c;
    }
}
