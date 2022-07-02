package com.taecel.modelo;

public class productosTaecelClienteModelo {

    private String bolsaID;
    private String categoria;
    private String categoriaID;
    private String carrier;
    private String carrierID;
    private String codigo;
    private String monto;
    private String unidades;
    private String vigencia;
    private String descripcion;
    private String nombre;
    private String bolsa;

    public String getBolsaID() {
        return bolsaID;
    }

    public void setBolsaID(String bolsaID) {
        this.bolsaID = bolsaID;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(String categoriaID) {
        this.categoriaID = categoriaID;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCarrierID() {
        return carrierID;
    }

    public void setCarrierID(String carrierID) {
        this.carrierID = carrierID;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBolsa() {
        return bolsa;
    }

    public void setBolsa(String bolsa) {
        this.bolsa = bolsa;
    }

    @Override
    public String toString() {
        return "productosTaecelClienteModelo{" + "bolsaID=" + bolsaID + ", categoria=" + categoria + ", categoriaID=" + categoriaID + ", carrier=" + carrier + ", carrierID=" + carrierID + ", codigo=" + codigo + ", monto=" + monto + ", unidades=" + unidades + ", vigencia=" + vigencia + ", descripcion=" + descripcion + ", nombre=" + nombre + ", bolsa=" + bolsa + '}';
    }

}
