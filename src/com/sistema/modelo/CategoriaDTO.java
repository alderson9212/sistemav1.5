/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.modelo;

/**
 *
 * @author wilmer
 */
public class CategoriaDTO {

    private Integer idcategoria;
    private String descripcion;

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "CategoriaDTO{" + "idcategoria=" + idcategoria + ", descripcion=" + descripcion + '}';
    }
  
    
}
