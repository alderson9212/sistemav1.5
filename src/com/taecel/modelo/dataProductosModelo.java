package com.taecel.modelo;

import java.util.ArrayList;

public class dataProductosModelo {

    private ArrayList<bolsaProductsModelo> bolsas = new ArrayList<>();
    private ArrayList<categoriasModelo> categorias = new ArrayList<>();
    private ArrayList<comisionTipoModelo> comisionTipo = new ArrayList<>();
    private ArrayList<carriersTipoModelo> carriersTipo = new ArrayList<>();
    private ArrayList<formatoCamposModelo> formatoCampos = new ArrayList<>();
    private ArrayList<carriersModelo> carriers = new ArrayList<>();
    private ArrayList<productoModel> productos = new ArrayList<>();

    public ArrayList<bolsaProductsModelo> getBolsas() {
        return bolsas;
    }

    public void setBolsas(ArrayList<bolsaProductsModelo> bolsas) {
        this.bolsas = bolsas;
    }

    public ArrayList<categoriasModelo> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<categoriasModelo> categorias) {
        this.categorias = categorias;
    }

    public ArrayList<comisionTipoModelo> getComisionTipo() {
        return comisionTipo;
    }

    public void setComisionTipo(ArrayList<comisionTipoModelo> comisionTipo) {
        this.comisionTipo = comisionTipo;
    }

    public ArrayList<carriersTipoModelo> getCarriersTipo() {
        return carriersTipo;
    }

    public void setCarriersTipo(ArrayList<carriersTipoModelo> carriersTipo) {
        this.carriersTipo = carriersTipo;
    }

    public ArrayList<formatoCamposModelo> getFormatoCampos() {
        return formatoCampos;
    }

    public void setFormatoCampos(ArrayList<formatoCamposModelo> formatoCampos) {
        this.formatoCampos = formatoCampos;
    }

    public ArrayList<carriersModelo> getCarriers() {
        return carriers;
    }

    public void setCarriers(ArrayList<carriersModelo> carriers) {
        this.carriers = carriers;
    }

    public ArrayList<productoModel> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<productoModel> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "dataProductosModelo{" + "bolsas=" + bolsas + ", categorias=" + categorias + ", comisionTipo=" + comisionTipo + ", carriersTipo=" + carriersTipo + ", formatoCampos=" + formatoCampos + ", carriers=" + carriers + ", productos=" + productos + '}';
    }

}
