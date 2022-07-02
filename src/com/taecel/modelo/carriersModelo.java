package com.taecel.modelo;


import java.util.ArrayList;

public class carriersModelo {

    private String ID;
    private String Nombre;
    private String Logotipo;
    private String BolsaID;
    private String Categoria;
    private String CategoriaID;
    private String Tipo;
    private String Promos;
    private String getSaldo;
    private String ScanQrName;
    private comisionModelo Comision;
    private ArrayList<campoModelo> Campos = new ArrayList<>();

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getLogotipo() {
        return Logotipo;
    }

    public void setLogotipo(String Logotipo) {
        this.Logotipo = Logotipo;
    }

    public String getBolsaID() {
        return BolsaID;
    }

    public void setBolsaID(String BolsaID) {
        this.BolsaID = BolsaID;
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

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getPromos() {
        return Promos;
    }

    public void setPromos(String Promos) {
        this.Promos = Promos;
    }

    public String getGetSaldo() {
        return getSaldo;
    }

    public void setGetSaldo(String getSaldo) {
        this.getSaldo = getSaldo;
    }

    public String getScanQrName() {
        return ScanQrName;
    }

    public void setScanQrName(String ScanQrName) {
        this.ScanQrName = ScanQrName;
    }

    public comisionModelo getComision() {
        return Comision;
    }

    public void setComision(comisionModelo Comision) {
        this.Comision = Comision;
    }

    public ArrayList<campoModelo> getCampos() {
        return Campos;
    }

    public void setCampos(ArrayList<campoModelo> Campos) {
        this.Campos = Campos;
    }

    @Override
    public String toString() {
        return "carriersModelo{" + "ID=" + ID + ", Nombre=" + Nombre + ", Logotipo=" + Logotipo + ", BolsaID=" + BolsaID + ", Categoria=" + Categoria + ", CategoriaID=" + CategoriaID + ", Tipo=" + Tipo + ", Promos=" + Promos + ", getSaldo=" + getSaldo + ", ScanQrName=" + ScanQrName + ", Comision=" + Comision + ", Campos=" + Campos + '}';
    }

}
