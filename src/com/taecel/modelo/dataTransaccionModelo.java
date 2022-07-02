package com.taecel.modelo;

public class dataTransaccionModelo {

    private String transID;

    public String getTransID() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }

    @Override
    public String toString() {
        return "dataTransaccionModelo{" + "transID=" + transID + '}';
    }

}
