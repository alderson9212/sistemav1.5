/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.modelo;

/**
 *
 * @author Wilmer
 */
public class ClienteDTO {

    private Integer idcliente;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String mail;
    private boolean activo;
    private String genero;
    private Integer idcolonia;

    public ClienteDTO() {
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getIdcolonia() {
        return idcolonia;
    }

    public void setIdcolonia(Integer idcolonia) {
        this.idcolonia = idcolonia;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" + "idcliente=" + idcliente + ", nombre=" + nombre + ", domicilio=" + domicilio + ", telefono=" + telefono + ", mail=" + mail + ", activo=" + activo + ", genero=" + genero + ", idcolonia=" + idcolonia + '}';
    }

}
