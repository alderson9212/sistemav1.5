package com.taecel.modelo;

public class formatoCamposModelo {

    private String ID;
    private String Nombre;

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

    @Override
    public String toString() {
        return "formatoCamposModelo{" + "ID=" + ID + ", Nombre=" + Nombre + '}';
    }

}
