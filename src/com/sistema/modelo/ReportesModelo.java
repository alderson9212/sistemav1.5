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
public class ReportesModelo {
    
    private String nombreNegocio;
    private String fechaInicial;
    private String fechaFinal;
    private List<DetalleVentaDTO>detallesVenta;
    private List<ProductoDTO>productos;

    public String getNombreNegocio() {
        return nombreNegocio;
    }

    public void setNombreNegocio(String nombreNegocio) {
        this.nombreNegocio = nombreNegocio;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public List<DetalleVentaDTO> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(List<DetalleVentaDTO> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "ReporteVentasDTO{" + "nombreNegocio=" + nombreNegocio + ", fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + ", detallesVenta=" + detallesVenta + ", productos=" + productos + '}';
    }
    
   
    
    
    
}
