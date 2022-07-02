package com.taecel.modelo;

public class bolsaBalanceModelo {

    private String ID;
    private String Bolsa;
    private String Saldo;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBolsa() {
        return Bolsa;
    }

    public void setBolsa(String Bolsa) {
        this.Bolsa = Bolsa;
    }

    public String getSaldo() {
        return Saldo;
    }

    public void setSaldo(String Saldo) {
        this.Saldo = Saldo;
    }

    @Override
    public String toString() {
        return "bolsaBalanceModelo{" + "ID=" + ID + ", Bolsa=" + Bolsa + ", Saldo=" + Saldo + '}';
    }

}
