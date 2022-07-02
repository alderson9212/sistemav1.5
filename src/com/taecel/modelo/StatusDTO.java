package com.taecel.modelo;

public class StatusDTO {

    private boolean success;
    private Integer error;
    private String message;
    private dataStatusModelo data;

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

    public dataStatusModelo getData() {
        return data;
    }

    public void setData(dataStatusModelo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StatusDTO{" + "success=" + success + ", error=" + error + ", message=" + message + ", data=" + data + '}';
    }

}
