package com.taecel.modelo;

public class comisionModelo {

    private String ID;
    private String CargoTrans;
    private String TipoCargo;
    private String AbonoTrans;
    private String TipoAbono;
    private String ComisionCliente;
    private String def_CargoTrans;
    private String def_AbonoTrans;
    private String Status;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCargoTrans() {
        return CargoTrans;
    }

    public void setCargoTrans(String CargoTrans) {
        this.CargoTrans = CargoTrans;
    }

    public String getTipoCargo() {
        return TipoCargo;
    }

    public void setTipoCargo(String TipoCargo) {
        this.TipoCargo = TipoCargo;
    }

    public String getAbonoTrans() {
        return AbonoTrans;
    }

    public void setAbonoTrans(String AbonoTrans) {
        this.AbonoTrans = AbonoTrans;
    }

    public String getTipoAbono() {
        return TipoAbono;
    }

    public void setTipoAbono(String TipoAbono) {
        this.TipoAbono = TipoAbono;
    }

    public String getComisionCliente() {
        return ComisionCliente;
    }

    public void setComisionCliente(String ComisionCliente) {
        this.ComisionCliente = ComisionCliente;
    }

    public String getDef_CargoTrans() {
        return def_CargoTrans;
    }

    public void setDef_CargoTrans(String def_CargoTrans) {
        this.def_CargoTrans = def_CargoTrans;
    }

    public String getDef_AbonoTrans() {
        return def_AbonoTrans;
    }

    public void setDef_AbonoTrans(String def_AbonoTrans) {
        this.def_AbonoTrans = def_AbonoTrans;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "comisionModelo{" + "ID=" + ID + ", CargoTrans=" + CargoTrans + ", TipoCargo=" + TipoCargo + ", AbonoTrans=" + AbonoTrans + ", TipoAbono=" + TipoAbono + ", ComisionCliente=" + ComisionCliente + ", def_CargoTrans=" + def_CargoTrans + ", def_AbonoTrans=" + def_AbonoTrans + ", Status=" + Status + '}';
    }

}
