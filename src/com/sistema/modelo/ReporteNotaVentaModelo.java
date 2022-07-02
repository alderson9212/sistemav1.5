/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.modelo;

import java.util.List;

/**
 *
 * @author Elliot
 */
public class ReporteNotaVentaModelo {
    
    private String nombreNegocio;
    private String fecha;
    private Double totalVenta;
    private List<DetalleVentaDTO>detallesVenta;

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }
   
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public List<DetalleVentaDTO> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(List<DetalleVentaDTO> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

    @Override
    public String toString() {
        return "ReporteNotaVentaModelo{" + "nombreNegocio=" + nombreNegocio + ", fecha=" + fecha + ", totalVenta=" + totalVenta + ", detallesVenta=" + detallesVenta + '}';
    }
    
    
   
    
    
    
}
