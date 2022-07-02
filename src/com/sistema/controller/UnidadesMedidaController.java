/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.controller;

import com.sistema.modelo.UnidadesMedidaDTO;
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
public class UnidadesMedidaController {

    conexion con = new conexion();
    Statement stmt;
    ResultSet rs;
    String sql = "";
    Connection connect = null;
    PreparedStatement ps;

    public UnidadesMedidaDTO unidadById(Integer id) {
        UnidadesMedidaDTO unidad = new UnidadesMedidaDTO();
        try {
            connect = con.connectDatabase();
            sql = "SELECT * FROM unidadesm WHERE idunidadm=" + id;
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                unidad.setIdunidadm(rs.getInt(1));
                unidad.setDescripcion(rs.getString(2));
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener undidad de medida por id:" + e.getMessage());
        }
        return unidad;
    }

    public List<UnidadesMedidaDTO> unidadesAll(String valor) {
        List<UnidadesMedidaDTO> ListaUnidad = new ArrayList<>();
        try {
            connect = con.connectDatabase();
            if(!valor.equals("")){
                sql = "SELECT * FROM unidadesm WHERE descripcion LIKE '%"+valor+"%'";
            }else{
                sql = "SELECT * FROM unidadesm";
            }
            
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                UnidadesMedidaDTO unidad = new UnidadesMedidaDTO();
                unidad.setIdunidadm(rs.getInt(1));
                unidad.setDescripcion(rs.getString(2));
                ListaUnidad.add(unidad);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener lista de unidades de medida:" + e.getMessage());
        }
        return ListaUnidad;
    }

    public UnidadesMedidaDTO unidadByNombre(String nombre) {
        UnidadesMedidaDTO unidad = new UnidadesMedidaDTO();
        try {
            connect = con.connectDatabase();
            sql = "SELECT * FROM unidadesm WHERE descripcion='" + nombre + "'";
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                unidad.setIdunidadm(rs.getInt(1));
                unidad.setDescripcion(rs.getString(2));
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener undidad de medida por descripcion:" + e.getMessage());
        }
        return unidad;
    }

    public boolean save(UnidadesMedidaDTO unidad) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "INSERT INTO unidadesm VALUES(?,?)";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, unidad.getIdunidadm());
            ps.setString(2, unidad.getDescripcion());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al insertar unidad:" + e.getMessage());
        }
        return bandera;
    }

    public boolean update(UnidadesMedidaDTO unidad) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "UPDATE unidadesm set descripcion =? WHERE idunidadm =?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, unidad.getDescripcion());
            ps.setInt(2, unidad.getIdunidadm());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al modificar unidad:" + e.getMessage());
        }
        return bandera;
    }

    public boolean delete(int id) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "DELETE FROM unidadesm WHERE idunidadm =?";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al eliminar unidad:" + e.getMessage());
        }
        return bandera;
    }

    public int generarSecuenciaId() {
        int c = 0;
        connect = con.connectDatabase();

        try {
            sql = "SELECT * FROM unidadesm ORDER BY idunidadm DESC LIMIT 1";
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
            System.out.println("Error al generar numeracion unidadesm:" + ex.getMessage());
        }
        return c;
    }
}
