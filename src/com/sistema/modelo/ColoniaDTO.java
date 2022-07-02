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
public class ColoniaDTO {
    
    private Integer idcolonia;
    private String nombre;;;;;
    private Integer idmunicipio;

    public Integer getIdcolonia() {
        return idcolonia;
    }

    public void setIdcolonia(Integer idcolonia) {
        this.idcolonia = idcolonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdmunicipio() {
        return idmunicipio;
    }

    public void setIdmunicipio(Integer idmunicipio) {
        this.idmunicipio = idmunicipio;
    }

    @Override
    public String toString() {
        return "ColoniaDTO{" + "idcolonia=" + idcolonia + ", nombre=" + nombre + ", idmunicipio=" + idmunicipio + '}';
    }
    
    
}
