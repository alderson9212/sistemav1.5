/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.controller;

import com.sistema.modelo.ColoniaDTO;
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
public class LocalidadesController {

    conexion con = new conexion();
    Statement stmt;
    ResultSet rs;
    String sql = "";
    Connection connect = null;
    PreparedStatement ps;

    public ColoniaDTO coloniaById(Integer id) {
        ColoniaDTO colonia = new ColoniaDTO();
        try {
            connect = con.connectDatabase();
            sql = "SELECT * FROM colonias WHERE idcolonia=" + id;
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                colonia.setIdcolonia(rs.getInt(1));
                colonia.setNombre(rs.getString(2));
                colonia.setIdmunicipio(rs.getInt(3));
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener colonia por id:" + e.getMessage());
        }
        return colonia;
    }

    public List<ColoniaDTO> coloniasAll(String valor) {
        List<ColoniaDTO> ListaColonias = new ArrayList<>();
        try {
            connect = con.connectDatabase();
            if(!valor.equals("")){
                sql = "SELECT * FROM colonias WHERE nombre LIKE '%"+valor+"%'";
            }else{
                sql = "SELECT * FROM colonias";
            }
            
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ColoniaDTO colonia = new ColoniaDTO();
                colonia.setIdcolonia(rs.getInt(1));
                colonia.setNombre(rs.getString(2));
                colonia.setIdmunicipio(rs.getInt(3));
                ListaColonias.add(colonia);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener lista de colonias de medida:" + e.getMessage());
        }
        return ListaColonias;
    }

    public ColoniaDTO coloniaByNombre(String nombre) {
        ColoniaDTO colonia = new ColoniaDTO();
        try {
            connect = con.connectDatabase();
            sql = "SELECT * FROM colonias WHERE nombre='" + nombre + "'";
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                colonia.setIdcolonia(rs.getInt(1));
                colonia.setNombre(rs.getString(2));
                colonia.setIdmunicipio(rs.getInt(3));
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener colonia por nombre:" + e.getMessage());
        }
        return colonia;
    }

    public boolean save(ColoniaDTO colonia) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "INSERT INTO colonias VALUES(?,?,?)";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, colonia.getIdcolonia());
            ps.setString(2, colonia.getNombre());
            ps.setInt(3, colonia.getIdmunicipio());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al insertar colonia:" + e.getMessage());
        }
        return bandera;
    }

    public boolean update(ColoniaDTO colonia) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "UPDATE colonias set nombre=?,idmunicipio=? WHERE idcolonia =?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, colonia.getNombre());
            ps.setInt(2, colonia.getIdmunicipio());
            ps.setInt(3,colonia.getIdcolonia());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al modificar colonia:" + e.getMessage());
        }
        return bandera;
    }

    public boolean delete(int id) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "DELETE FROM colonias WHERE idcolonia =?";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al eliminar colonia:" + e.getMessage());
        }
        return bandera;
    }

    public int generarSecuenciaId() {
        int c = 0;
        connect = con.connectDatabase();

        try {
            sql = "SELECT * FROM colonias ORDER BY idcolonia DESC LIMIT 1";
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
            System.out.println("Error al generar numeracion colonias:" + ex.getMessage());
        }
        return c;
    }
}
