package com.taecel.modelo;

public class categoriasModelo {

    private String ID;
    private String Nombre;
    private String Icono;

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

    public String getIcono() {
        return Icono;
    }

    public void setIcono(String Icono) {
        this.Icono = Icono;
    }

    @Override
    public String toString() {
        return "categoriasModelo{" + "ID=" + ID + ", Nombre=" + Nombre + ", Icono=" + Icono + '}';
    }

}
