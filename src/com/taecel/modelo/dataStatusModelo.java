package com.taecel.modelo;

public class dataStatusModelo {

    private String TransID;
    private String Fecha;
    private String Carrier;
    private String Telefono;
    private String Folio;
    private String Status;
    private String Monto;
    private String Cargo;
    private String Abono;
    private String Via;
    private String Región;
    private String Timeout;
    private String IP;
    private String Bolsa;
    private String Comision;
    private String Nota;
    private String pin;
    private String SaldoFinal;

    public String getTransID() {
        return TransID;
    }

    public void setTransID(String TransID) {
        this.TransID = TransID;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getCarrier() {
        return Carrier;
    }

    public void setCarrier(String Carrier) {
        this.Carrier = Carrier;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getFolio() {
        return Folio;
    }

    public void setFolio(String Folio) {
        this.Folio = Folio;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getMonto() {
        return Monto;
    }

    public void setMonto(String Monto) {
        this.Monto = Monto;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getAbono() {
        return Abono;
    }

    public void setAbono(String Abono) {
        this.Abono = Abono;
    }

    public String getVia() {
        return Via;
    }

    public void setVia(String Via) {
        this.Via = Via;
    }

    public String getRegión() {
        return Región;
    }

    public void setRegión(String Región) {
        this.Región = Región;
    }

    public String getTimeout() {
        return Timeout;
    }

    public void setTimeout(String Timeout) {
        this.Timeout = Timeout;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getBolsa() {
        return Bolsa;
    }

    public void setBolsa(String Bolsa) {
        this.Bolsa = Bolsa;
    }

    public String getComision() {
        return Comision;
    }

    public void setComision(String Comision) {
        this.Comision = Comision;
    }

    public String getNota() {
        return Nota;
    }

    public void setNota(String Nota) {
        this.Nota = Nota;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getSaldoFinal() {
        return SaldoFinal;
    }

    public void setSaldoFinal(String SaldoFinal) {
        this.SaldoFinal = SaldoFinal;
    }

    @Override
    public String toString() {
        return "dataStatusModelo{" + "TransID=" + TransID + ", Fecha=" + Fecha + ", Carrier=" + Carrier + ", Telefono=" + Telefono + ", Folio=" + Folio + ", Status=" + Status + ", Monto=" + Monto + ", Cargo=" + Cargo + ", Abono=" + Abono + ", Via=" + Via + ", Regi\u00f3n=" + Región + ", Timeout=" + Timeout + ", IP=" + IP + ", Bolsa=" + Bolsa + ", Comision=" + Comision + ", Nota=" + Nota + ", pin=" + pin + ", SaldoFinal=" + SaldoFinal + '}';
    }

}
