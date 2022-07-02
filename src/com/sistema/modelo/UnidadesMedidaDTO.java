/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.modelo;

import java.io.Serializable;
/**
 *
 * @author Anonymous
 */
public class UnidadesMedidaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer idunidadm;
    private String descripcion;

    public UnidadesMedidaDTO() {
    }

    public UnidadesMedidaDTO(Integer idunidadm, String descripcion) {
        this.idunidadm = idunidadm;
        this.descripcion = descripcion;
    }

    public Integer getIdunidadm() {
        return idunidadm;
    }

    public void setIdunidadm(Integer idunidadm) {
        this.idunidadm = idunidadm;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Unidadesm{" + "idunidadm=" + idunidadm + ", descripcion=" + descripcion + '}';
    }

    
}
