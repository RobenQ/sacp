package com.sacp.admin.response;

import java.io.Serializable;

public class AdminResponse<T> implements Serializable {
    private int code;
    private String token;
    private String message;
    private String status;
    private T result;

    public AdminResponse() {
    }

    public AdminResponse(int code,String token, String message, String status, T result) {
        this.code = code;
        this.token = token;
        this.message = message;
        this.status = status;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AdminResponse{" +
                "code=" + code +
                ", token='" + token + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", result=" + result +
                '}';
    }
}
