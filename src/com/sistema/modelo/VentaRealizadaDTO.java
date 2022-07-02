/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.modelo;

import java.sql.Timestamp;

/**
 *
 * @author wilmer
 */
public class VentaRealizadaDTO {
   private Integer idticket;
   private Timestamp fecha;
   private String tipoventa;
   private Double totalventa;
   private Integer idusuario;

    public VentaRealizadaDTO() {
    }

    public Integer getIdticket() {
        return idticket;
    }

    public void setIdticket(Integer idticket) {
        this.idticket = idticket;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getTipoventa() {
        return tipoventa;
    }

    public void setTipoventa(String tipoventa) {
        this.tipoventa = tipoventa;
    }

    public Double getTotalventa() {
        return totalventa;
    }

    public void setTotalventa(Double totalventa) {
        this.totalventa = totalventa;
    }

    public int getIdUsuario() {
        return idusuario;
    }

    public void setUsuario(int idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public String toString() {
        return "VentaRealizadaDTO{" + "idticket=" + idticket + ", fecha=" + fecha + ", tipoventa=" + tipoventa + ", totalventa=" + totalventa + ", idusuario=" + idusuario + '}';
    }
   
   
}
