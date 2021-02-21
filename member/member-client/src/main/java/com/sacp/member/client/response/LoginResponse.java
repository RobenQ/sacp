package com.sacp.member.client.response;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private String nickName;
    private String password;
    private Integer statusCode;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginResponse(String nickName, String password, Integer statusCode) {
        this.nickName = nickName;
        this.password = password;
        this.statusCode = statusCode;
    }
}
