/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.controller;

import com.sistema.modelo.CategoriaDTO;
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
public class CategoriaController {

    conexion con = new conexion();
    Connection connect = null;
    Statement stmt;
    ResultSet rs;
    String sql = "";
    PreparedStatement ps;

    public List<CategoriaDTO> categoriasAll(String valor) {
        List<CategoriaDTO> lista = new ArrayList<>();
        connect = con.connectDatabase();
        try {
            if (!sql.equals("")) {
                 sql = "SELECT * FROM categorias WHERE descripcion LIKE '%"+valor+"%'";
            } else {
                 sql = "SELECT * FROM categorias";
            }
           
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CategoriaDTO cat = new CategoriaDTO();
                cat.setIdcategoria(rs.getInt(1));
                cat.setDescripcion(rs.getString(2));

                lista.add(cat);
            }
            connect.close();
        } catch (Exception ex) {
            System.out.println("Error al obtener lista de categorias:" + ex.getMessage());
        }
        return lista;
    }

    public CategoriaDTO categoriaById(int id) {
        CategoriaDTO categoria = new CategoriaDTO();
        connect = con.connectDatabase();
        try {
            sql = "SELECT * FROM categorias WHERE idcategoria =" + id;
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                categoria.setIdcategoria(rs.getInt(1));
                categoria.setDescripcion(rs.getString(2));
            }
            connect.close();
        } catch (Exception ex) {
            System.out.println("Error al obtener categoria por id:" + ex.getMessage());
        }
        return categoria;
    }

    public CategoriaDTO categoriaByNombre(String nombre) {
        CategoriaDTO categoria = new CategoriaDTO();
        connect = con.connectDatabase();
        try {
            sql = "SELECT * FROM categorias WHERE descripcion='" + nombre + "'";
            stmt = connect.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                categoria.setIdcategoria(rs.getInt(1));
                categoria.setDescripcion(rs.getString(2));
            }
            connect.close();
        } catch (Exception ex) {
            System.out.println("Error al obtener categoria por nombre:" + ex.getMessage());
        }
        return categoria;
    }

    public boolean save(CategoriaDTO cat) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "INSERT INTO categorias VALUES(?,?)";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, cat.getIdcategoria());
            ps.setString(2, cat.getDescripcion());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al insertar categoria:" + e.getMessage());
        }
        return bandera;
    }

    public boolean update(CategoriaDTO cat) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "UPDATE categorias set descripcion =? WHERE idcategoria =?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, cat.getDescripcion());
            ps.setInt(2, cat.getIdcategoria());
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al modificar categoria:" + e.getMessage());
        }
        return bandera;
    }

    public boolean delete(int id) {
        boolean bandera = false;
        connect = con.connectDatabase();
        try {
            sql = "DELETE FROM categorias WHERE idcategoria = ?";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            connect.close();
            bandera = true;
        } catch (Exception e) {
            System.out.println("Error al  al eliminar categoria:" + e.getMessage());
        }
        return bandera;
    }

    public int generarSecuenciaId() {
        int c = 0;
        connect = con.connectDatabase();

        try {
            sql = "SELECT * FROM categorias ORDER BY idcategoria DESC LIMIT 1";
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
            System.out.println("Error al generar numeracion categoria:" + ex.getMessage());
        }
        return c;
    }
}
