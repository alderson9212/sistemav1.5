package com.sistema.modelo;



public class ProveedorDTO { 
   
    private static final long serialVersionUID = 1L;
   
    private Integer idproveedor;
    private String nombre;
    private String telefono;
    private String mail;

    public ProveedorDTO() {
    }

    public ProveedorDTO(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Integer getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(Integer idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "ProveedorDTO{" + "idproveedor=" + idproveedor + ", nombre=" + nombre + ", telefono=" + telefono + ", mail=" + mail + '}';
    }

  
    

}