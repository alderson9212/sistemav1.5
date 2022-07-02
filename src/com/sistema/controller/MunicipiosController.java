/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.controller;

import com.sistema.modelo.MunicipioDTO;
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
public class MunicipiosController {

    conexion con = new conexion();
    Statement stmt;
    ResultSet rs;
    String sql = "";
    Connection connect = null;
    PreparedStatement ps;

    public MunicipioDTO municipioById(Integer id) {
        MunicipioDTO municipio = new MunicipioDTO();
        try {
            connect = con.connectDatabase();
            sql = "SELECT * FROM municipios WHERE idmunicipio=" + id;
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                municipio.setIdmunicipio(rs.getInt(1));
                municipio.setNombre(rs.getString(2));
                municipio.setIdestado(rs.getInt(3));
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener municipio por id:" + e.getMessage());
        }
        return municipio;
    }

    public List<MunicipioDTO> municipiosAll(String valor) {
        List<MunicipioDTO> ListaMunicipios = new ArrayList<>();
        try {
            connect = con.connectDatabase();
            if(!valor.equals("")){
                sql = "SELECT * FROM municipios WHERE nombre LIKE '%"+valor+"%'";
            }else{
                sql = "SELECT * FROM municipios";
            }
            
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                MunicipioDTO municipio = new MunicipioDTO();
                municipio.setIdmunicipio(rs.getInt(1));
                municipio.setNombre(rs.getString(2));
                municipio.setIdestado(rs.getInt(3));
                ListaMunicipios.add(municipio);
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener lista de municipios:" + e.getMessage());
        }
        return ListaMunicipios;
    }

    public MunicipioDTO municipioByNombre(String nombre) {
       MunicipioDTO municipio = new MunicipioDTO();
        try {
            connect = con.connectDatabase();
            sql = "SELECT * FROM municipios WHERE nombre='" + nombre + "'";
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                municipio.setIdmunicipio(rs.getInt(1));
                municipio.setNombre(rs.getString(2));
                municipio.setIdestado(rs.getInt(2));
            }
            connect.close();
        } catch (Exception e) {
            System.out.println("Error al obtener municipio por nombre:" + e.getMessage());
        }
        return municipio;
    }

    public boolean save(MunicipioDTO municipio) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "INSERT INTO municipios VALUES(?,?,?)";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, municipio.getIdmunicipio());
            ps.setString(2, municipio.getNombre());
            ps.setInt(3, municipio.getIdestado());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al insertar municipio:" + e.getMessage());
        }
        return bandera;
    }

    public boolean update(MunicipioDTO municipio) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "UPDATE municipios set nombre =?,idestado=? WHERE idmunicipio =?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, municipio.getNombre());
            ps.setInt(2,municipio.getIdestado());
            ps.setInt(3,municipio.getIdmunicipio());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al modificar municipio:" + e.getMessage());
        }
        return bandera;
    }

    public boolean delete(int id) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "DELETE FROM municipios WHERE idmunicipio =?";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al eliminar municipio:" + e.getMessage());
        }
        return bandera;
    }

    public int generarSecuenciaId() {
        int c = 0;
        connect = con.connectDatabase();

        try {
            sql = "SELECT * FROM municipios ORDER BY idmunicipio DESC LIMIT 1";
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
            System.out.println("Error al generar numeracion municipios:" + ex.getMessage());
        }
        return c;
    }
}
