package com.taecel.modelo;

public class TransaccionDTO {

    private boolean success;
    private Integer error;
    private String message;
    private dataTransaccionModelo data;

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

    public dataTransaccionModelo getData() {
        return data;
    }

    public void setData(dataTransaccionModelo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TransaccionDTO{" + "success=" + success + ", error=" + error + ", message=" + message + ", data=" + data + '}';
    }

}
