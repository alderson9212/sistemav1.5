/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.controller;

import com.sistema.modelo.ProductoDTO;
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
public class ProductoController {

    conexion con = new conexion();
    Connection connect = null;
    Statement stmt;
    ResultSet rs;
    PreparedStatement ps;
    String sql = "";

    public List<ProductoDTO> productosAll(String nombre) {
        List<ProductoDTO> lista = new ArrayList<>();
        connect = con.connectDatabase();
        try {
            if (nombre.trim().equals("")) {
                sql = "SELECT * FROM productos order by idproducto";
            } else {
                sql = "SELECT * FROM productos WHERE nombre LIKE '%" + nombre.toUpperCase() + "%'";
            }
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProductoDTO producto = new ProductoDTO();
                producto.setIdproducto(rs.getInt(1));
                producto.setNombre(rs.getString(2).trim());
                producto.setPrecio(rs.getDouble(3));
                producto.setPreciocliente(rs.getDouble(4));
                producto.setStock(rs.getInt(5));
                producto.setActivarpreciocliente(rs.getBoolean(6));
                producto.setPreciodeproveedor(rs.getDouble(7));
                producto.setIdcategoria(rs.getInt(8));
                producto.setIdproveedor(rs.getInt(9));
                producto.setIdunidadm(rs.getInt(10));
                lista.add(producto);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener lista de productos:" + e.getMessage());
        }
        return lista;
    }
    
    
      public List<ProductoDTO> productosReporte(String nombre,int opcion) {
        List<ProductoDTO> lista = new ArrayList<>();
        connect = con.connectDatabase();
        try {
            if (opcion ==1 && !nombre.trim().equals("")) {
                sql = "SELECT * FROM productos WHERE nombre LIKE '%" + nombre.toUpperCase() + "%'";
           }else if(opcion == 2 && !nombre.trim().equals("")){
                sql = "SELECT * FROM productos WHERE idproveedor=" + Integer.parseInt(nombre.toUpperCase());
           }else if(opcion==3 && !nombre.equals("")){
            sql = "SELECT * FROM productos WHERE idunidadm=" + Integer.parseInt(nombre.toUpperCase());   
           }else{    
                sql = "SELECT * FROM productos WHERE nombre LIKE '%" + nombre.toUpperCase() + "%'";
           }
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ProductoDTO producto = new ProductoDTO();
                producto.setIdproducto(rs.getInt(1));
                producto.setNombre(rs.getString(2).trim());
                producto.setPrecio(rs.getDouble(3));
                producto.setPreciocliente(rs.getDouble(4));
                producto.setStock(rs.getInt(5));
                producto.setActivarpreciocliente(rs.getBoolean(6));
                producto.setPreciodeproveedor(rs.getDouble(7));
                producto.setIdcategoria(rs.getInt(8));
                producto.setIdproveedor(rs.getInt(9));
                producto.setIdunidadm(rs.getInt(10));
                lista.add(producto);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener lista de productos:" + e.getMessage());
        }
        return lista;
    }

    public ProductoDTO productoById(int id) {
        ProductoDTO producto = new ProductoDTO();
        connect = con.connectDatabase();
        try {
            sql = "SELECT * FROM productos WHERE idproducto=" + id;
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                producto = new ProductoDTO();
                producto.setIdproducto(rs.getInt(1));
                producto.setNombre(rs.getString(2).trim());
                producto.setPrecio(rs.getDouble(3));
                producto.setPreciocliente(rs.getDouble(4));
                producto.setStock(rs.getInt(5));
                producto.setActivarpreciocliente(rs.getBoolean(6));
                producto.setPreciodeproveedor(rs.getDouble(7));
                producto.setIdcategoria(rs.getInt(8));
                producto.setIdproveedor(rs.getInt(9));
                producto.setIdunidadm(rs.getInt(10));
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener producto por id:" + e.getMessage());
        }
        return producto;
    }
    
      public ProductoDTO productoByNombre(String nombre) {
        ProductoDTO producto = new ProductoDTO();
        connect = con.connectDatabase();
        try {
            sql = "SELECT * FROM productos WHERE nombre='" +nombre+"'";
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                producto = new ProductoDTO();
                producto.setIdproducto(rs.getInt(1));
                producto.setNombre(rs.getString(2).trim());
                producto.setPrecio(rs.getDouble(3));
                producto.setPreciocliente(rs.getDouble(4));
                producto.setStock(rs.getInt(5));
                producto.setActivarpreciocliente(rs.getBoolean(6));
                producto.setPreciodeproveedor(rs.getDouble(7));
                producto.setIdcategoria(rs.getInt(8));
                producto.setIdproveedor(rs.getInt(9));
                producto.setIdunidadm(rs.getInt(10));
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener producto por id:" + e.getMessage());
        }
        return producto;
    }


    public int modificarProductoStock(int id, int stock) {
        sql = "UPDATE productos set stock =" + stock + " WHERE idproducto=" + id;
        connect = con.connectDatabase();
        int modificados = 0;
        try {
            ps = connect.prepareStatement(sql);
            modificados = ps.executeUpdate();
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al modificar un producto:" + e.getMessage());
        }
        return modificados;
    }

    public boolean save(ProductoDTO producto) {
        boolean bandera = false;
        connect = con.connectDatabase();
        System.out.println("" + producto);
        try {
            sql = "INSERT INTO productos VALUES(?,?,?,?,?,?,?,?,?,?)";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, producto.getIdproducto());
            ps.setString(2, producto.getNombre().trim());
            ps.setDouble(3, producto.getPrecio());
            ps.setDouble(4, producto.getPreciocliente());
            ps.setInt(5, producto.getStock());
            ps.setBoolean(6, producto.isActivarpreciocliente());
            ps.setDouble(7, producto.getPreciodeproveedor());
            ps.setInt(8, producto.getIdcategoria());
            ps.setInt(9, producto.getIdproveedor());
            ps.setInt(10, producto.getIdunidadm());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al insertar producto:" + e.getMessage());
        }
        return bandera;
    }

    public boolean update(ProductoDTO producto) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "UPDATE productos set nombre=?,precio=?,preciocliente=?,stock=?,activarpreciocliente=?,preciodeproveedor=?,idcategoria=?,idproveedor=?,idunidadm=? WHERE idproducto=?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, producto.getNombre().trim());
            ps.setDouble(2, producto.getPrecio());
            ps.setDouble(3,producto.getPreciocliente());
            ps.setInt(4,producto.getStock());
            ps.setBoolean(5, producto.isActivarpreciocliente());
            ps.setDouble(6, producto.getPreciodeproveedor());
            ps.setInt(7,producto.getIdcategoria());
            ps.setInt(8,producto.getIdproveedor());
            ps.setInt(9,producto.getIdunidadm());
            ps.setInt(10,producto.getIdproducto());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al modificar producto:" + e.getMessage());
        }
        return bandera;
    }
    
     public boolean detelete(int id) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "DELETE FROM productos WHERE idproducto=?";
            ps = connect.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al eliminar producto:" + e.getMessage());
        }
        return bandera;
    }

    public int generarSecuenciaId() {
        int c = 0;
        connect = con.connectDatabase();

        try {
            sql = "SELECT * FROM productos ORDER BY idproducto DESC LIMIT 1";
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
            System.out.println("Error al generar numeracion productos:" + ex.getMessage());
        }
        return c;
    }
}
