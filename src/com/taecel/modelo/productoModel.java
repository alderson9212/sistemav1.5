package com.taecel.modelo;

public class productoModel {

    private String Bolsa;
    private String Categoria;
    private String CategoriaID;
    private String BolsaID;
    private String Carrier;
    private String CarrierID;
    private String Codigo;
    private String Monto;
    private String Unidades;
    private String Vigencia;
    private String Descripcion;
    private String Nombre;

    public String getBolsa() {
        return Bolsa;
    }

    public void setBolsa(String Bolsa) {
        this.Bolsa = Bolsa;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getCategoriaID() {
        return CategoriaID;
    }

    public void setCategoriaID(String CategoriaID) {
        this.CategoriaID = CategoriaID;
    }

    public String getBolsaID() {
        return BolsaID;
    }

    public void setBolsaID(String BolsaID) {
        this.BolsaID = BolsaID;
    }

    public String getCarrier() {
        return Carrier;
    }

    public void setCarrier(String Carrier) {
        this.Carrier = Carrier;
    }

    public String getCarrierID() {
        return CarrierID;
    }

    public void setCarrierID(String CarrierID) {
        this.CarrierID = CarrierID;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getMonto() {
        return Monto;
    }

    public void setMonto(String Monto) {
        this.Monto = Monto;
    }

    public String getUnidades() {
        return Unidades;
    }

    public void setUnidades(String Unidades) {
        this.Unidades = Unidades;
    }

    public String getVigencia() {
        return Vigencia;
    }

    public void setVigencia(String Vigencia) {
        this.Vigencia = Vigencia;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "productoModel{" + "Bolsa=" + Bolsa + ", Categoria=" + Categoria + ", CategoriaID=" + CategoriaID + ", BolsaID=" + BolsaID + ", Carrier=" + Carrier + ", CarrierID=" + CarrierID + ", Codigo=" + Codigo + ", Monto=" + Monto + ", Unidades=" + Unidades + ", Vigencia=" + Vigencia + ", Descripcion=" + Descripcion + ", Nombre=" + Nombre + '}';
    }

}
