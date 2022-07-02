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
public class TiposVentaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer idtipoventa;
    private String descripcion;

    public TiposVentaDTO() {
    }

    public TiposVentaDTO(Integer idtipoventa, String descripcion) {
        this.idtipoventa = idtipoventa;
        this.descripcion = descripcion;
    }

    public Integer getIdtipoventa() {
        return idtipoventa;
    }

    public void setIdtipoventa(Integer idtipoventa) {
        this.idtipoventa = idtipoventa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TiposVenta{" + "idtipoventa=" + idtipoventa + ", descripcion=" + descripcion + '}';
    }
    
        
}
