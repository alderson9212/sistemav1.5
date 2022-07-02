package com.taecel.modelo;

public class ProductsDTO {

    private boolean success;
    private Integer error;
    private String message;
    private dataProductosModelo data;

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

    public dataProductosModelo getData() {
        return data;
    }

    public void setData(dataProductosModelo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProductsDTO{" + "success=" + success + ", error=" + error + ", message=" + message + ", data=" + data + '}';
    }

}
