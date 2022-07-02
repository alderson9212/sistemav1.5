package com.taecel.modelo;

public class campoModelo {

    private String Nombre;
    private String Campo;
    private String Min;
    private String Max;
    private String Formato;
    private String Confirmar;
    private String Obligatorio;
    private String iniCero;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCampo() {
        return Campo;
    }

    public void setCampo(String Campo) {
        this.Campo = Campo;
    }

    public String getMin() {
        return Min;
    }

    public void setMin(String Min) {
        this.Min = Min;
    }

    public String getMax() {
        return Max;
    }

    public void setMax(String Max) {
        this.Max = Max;
    }

    public String getFormato() {
        return Formato;
    }

    public void setFormato(String Formato) {
        this.Formato = Formato;
    }

    public String getConfirmar() {
        return Confirmar;
    }

    public void setConfirmar(String Confirmar) {
        this.Confirmar = Confirmar;
    }

    public String getObligatorio() {
        return Obligatorio;
    }

    public void setObligatorio(String Obligatorio) {
        this.Obligatorio = Obligatorio;
    }

    public String getIniCero() {
        return iniCero;
    }

    public void setIniCero(String iniCero) {
        this.iniCero = iniCero;
    }

    @Override
    public String toString() {
        return "campoModelo{" + "Nombre=" + Nombre + ", Campo=" + Campo + ", Min=" + Min + ", Max=" + Max + ", Formato=" + Formato + ", Confirmar=" + Confirmar + ", Obligatorio=" + Obligatorio + ", iniCero=" + iniCero + '}';
    }

}
