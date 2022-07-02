/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.controller;

import com.sistema.modelo.DetalleVentaDTO;
import com.sistema.modelo.TiposVentaDTO;
import com.sistema.modelo.VentaRealizadaDTO;
import com.sistema.util.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author wilmer
 */
public class VentasController {   
    conexion con=new conexion();   
    Statement stmt;
    Connection connect = null;
    PreparedStatement ps;
    String sql = "";
    ResultSet rs;
    public List<TiposVentaDTO> tiposVenta(){        
        sql = "SELECT * FROM tiposventa ORDER BY idtipo DESC";        
        List<TiposVentaDTO>listaTiposVenta = new ArrayList<>();
        try {
            connect = con.connectDatabase();    
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                TiposVentaDTO tipo = new TiposVentaDTO();
                tipo.setIdtipoventa(rs.getInt(1));
                tipo.setDescripcion(rs.getString(2));
                listaTiposVenta.add(tipo);
            }            
         connect.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener lista de tipos venta:"+ex.getMessage());
        }   
        
        return listaTiposVenta;
    }   
    
    public int insertarVentaRealizada(VentaRealizadaDTO venta){
         sql = "INSERT INTO ventas_realizadas VALUES(?,?,?,?,?)";
         int registros = 0;
         try {
             connect = con.connectDatabase();    
             ps = connect.prepareStatement(sql);
             ps.setInt(1,venta.getIdticket());
             ps.setTimestamp(2, venta.getFecha());
             ps.setString(3, venta.getTipoventa());
             ps.setDouble(4, venta.getTotalventa());
             ps.setInt(5, venta.getIdUsuario());
            registros = ps.executeUpdate();
             connect.close();
         } catch (Exception e) {
             System.out.println("Error al insertar ventas realizadas:"+e.getMessage());
         }
        return registros;
    }
    
    public int insertarDetalleVenta(DetalleVentaDTO detalle){
        sql = "INSERT INTO detalles_venta VALUES(?,?,?,?,?)";
         int registros = 0;
         try {
             connect = con.connectDatabase();    
             ps = connect.prepareStatement(sql);
             ps.setInt(1,detalle.getTicket());
             ps.setString(2, detalle.getProducto());
             ps.setInt(3, detalle.getTotalProducto());
             ps.setDouble(4, detalle.getTotal());
             ps.setString(5, detalle.getCliente());
            registros = ps.executeUpdate();
             connect.close();
         } catch (Exception e) {
             System.out.println("Error al insertar detalle_ventas:"+e.getMessage());
         }
         return registros;
    }
    
    public List<DetalleVentaDTO>listaDetallesVenta(String fechaInit,String fechaFin){
        List<DetalleVentaDTO>lista = new ArrayList<>();
        connect = con.connectDatabase();
        try {
            if (fechaInit != null && fechaFin!=null) {
                sql = "SELECT * FROM detalles_venta dv INNER JOIN ventas_realizadas vr USING(idticket) WHERE CAST(date(vr.fecha) AS CHAR(10)) BETWEEN '"+fechaInit+"' AND '"+fechaFin+"' ORDER BY idticket";
            } else {
                sql = "SELECT * FROM detalles_venta dv INNER JOIN ventas_realizadas vr USING(idticket) ORDER BY vr.idticket";
            }
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                DetalleVentaDTO detalles = new DetalleVentaDTO();
                detalles.setTicket(rs.getInt(1));
                detalles.setProducto(rs.getString(2));
                detalles.setTotalProducto(rs.getInt(3));
                detalles.setTotal(rs.getDouble(4));
                detalles.setCliente(rs.getString(5));
                lista.add(detalles);            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener lista detalles venta:" + e.getMessage());
        }
        return lista;
    }
    
}
