/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.controller;

import com.sistema.modelo.EstadoDTO;
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
public class EstadosController {

    conexion con = new conexion();
    Statement stmt;
    ResultSet rs;
    String sql = "";
    Connection connect = null;
    PreparedStatement ps;

    public EstadoDTO estadoById(Integer id) {
        EstadoDTO estado = new EstadoDTO();
        try {
            connect = con.connectDatabase();
            sql = "SELECT * FROM estados WHERE idestado=" + id;
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                estado.setId(id);
                estado.setNombre(rs.getString(2));
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener estado por id:" + e.getMessage());
        }
        return estado;
    }

    public List<EstadoDTO> estadosAll(String valor) {
        List<EstadoDTO> ListaEstados = new ArrayList<>();
        try {
            connect = con.connectDatabase();
            if(!valor.equals("")){
                sql = "SELECT * FROM estados WHERE nombre LIKE '%"+valor+"%'";
            }else{
                sql = "SELECT * FROM estados";
            }
            
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
               EstadoDTO estado = new EstadoDTO();
                estado.setId(rs.getInt(1));
                estado.setNombre(rs.getString(2));
                ListaEstados.add(estado);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener lista de estados:" + e.getMessage());
        }
        return ListaEstados;
    }

    public EstadoDTO estadoByNombre(String nombre) {
        EstadoDTO estado = new EstadoDTO();
        try {
            connect = con.connectDatabase();
            sql = "SELECT * FROM estados WHERE nombre='" + nombre + "'";
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                estado.setId(rs.getInt(1));
                estado.setNombre(rs.getString(2));
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener estado por nombre:" + e.getMessage());
        }
        return estado;
    }

    public boolean save(EstadoDTO estado) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "INSERT INTO estados VALUES(?,?)";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, estado.getId());
            ps.setString(2, estado.getNombre());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al insertar estado:" + e.getMessage());
        }
        return bandera;
    }

    public boolean update(EstadoDTO estado) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "UPDATE estados set nombre=? WHERE idestado =?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, estado.getNombre());
            ps.setInt(2, estado.getId());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al modificar estado:" + e.getMessage());
        }
        return bandera;
    }

    public boolean delete(int id) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "DELETE FROM estados WHERE idestado =?";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al eliminar estado:" + e.getMessage());
        }
        return bandera;
    }

    public int generarSecuenciaId() {
        int c = 0;
        connect = con.connectDatabase();

        try {
            sql = "SELECT * FROM estados ORDER BY idestado DESC LIMIT 1";
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
            System.out.println("Error al generar numeracion estados:" + ex.getMessage());
        }
        return c;
    }
}
