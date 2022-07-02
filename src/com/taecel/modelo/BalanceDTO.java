package com.taecel.modelo;

import java.util.ArrayList;
import java.util.List;

public class BalanceDTO {

    private boolean success;
    private Integer error;
    private String message;
    private List<bolsaBalanceModelo> data = new ArrayList<bolsaBalanceModelo>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<bolsaBalanceModelo> getData() {
        return data;
    }

    public void setData(List<bolsaBalanceModelo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BalanceDTO{" + "success=" + success + ", error=" + error + ", message=" + message + ", data=" + data + '}';
    }

}
