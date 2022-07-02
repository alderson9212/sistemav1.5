/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.modelo;

import java.io.Serializable;

/**
 *
 * @author Elliot
 */
public class ProductoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idproducto;
    private String nombre;
    private Double precio;
    private Double preciocliente;
    private Integer stock;
    private boolean activarpreciocliente;
    private Double preciodeproveedor;
    private Integer idcategoria;
    private Integer idproveedor;
    private Integer idunidadm;

    public ProductoDTO() {

    }

    public ProductoDTO(Integer idproducto, String nombre, Double precio, Double preciocliente, Integer stock, boolean activarpreciocliente, Double preciodeproveedor, Integer idcategoria, Integer idproveedor, Integer idunidadm) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.precio = precio;
        this.preciocliente = preciocliente;
        this.stock = stock;
        this.activarpreciocliente = activarpreciocliente;
        this.preciodeproveedor = preciodeproveedor;
        this.idcategoria = idcategoria;
        this.idproveedor = idproveedor;
        this.idunidadm = idunidadm;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPreciocliente() {
        return preciocliente;
    }

    public void setPreciocliente(Double preciocliente) {
        this.preciocliente = preciocliente;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public boolean isActivarpreciocliente() {
        return activarpreciocliente;
    }

    public void setActivarpreciocliente(boolean activarpreciocliente) {
        this.activarpreciocliente = activarpreciocliente;
    }

    public Double getPreciodeproveedor() {
        return preciodeproveedor;
    }

    public void setPreciodeproveedor(Double preciodeproveedor) {
        this.preciodeproveedor = preciodeproveedor;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public Integer getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Integer getIdunidadm() {
        return idunidadm;
    }

    public void setIdunidadm(Integer idunidadm) {
        this.idunidadm = idunidadm;
    }

    @Override
    public String toString() {
        return "Productos{" + "idproducto=" + idproducto + ", nombre=" + nombre + ", precio=" + precio + ", preciocliente=" + preciocliente + ", stock=" + stock + ", activarpreciocliente=" + activarpreciocliente + ", preciodeproveedor=" + preciodeproveedor + ", idcategoria=" + idcategoria + ", idproveedor=" + idproveedor + ", idunidadm=" + idunidadm + '}';
    }

}
