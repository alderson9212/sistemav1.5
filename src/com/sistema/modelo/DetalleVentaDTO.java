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
public class DetalleVentaDTO {
    
    private Integer ticket;
    private String producto;
    private Integer totalProducto;
    private Double total;
    private String cliente;

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getTotalProducto() {
        return totalProducto;
    }

    public void setTotalProducto(Integer totalProducto) {
        this.totalProducto = totalProducto;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "DetalleVentaDTO{" + "ticket=" + ticket + ", producto=" + producto + ", totalProducto=" + totalProducto + ", total=" + total + ", cliente=" + cliente + '}';
    }
    
    
    
}
