package com.sacp.web.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class UserResponse<T> implements Serializable {
    private int code;
    private String token;
    private String sacpId;
    private String avatar;
    private String introduction;
    private List<String> roles;
    private String message;
    private String status;
    private T result;

    public static UserResponse<Object> buildSuccess(){
        UserResponse<Object> response = new UserResponse<>();
        response.setCode(200);
        response.setMessage("success");
        return response;
    }

    public static UserResponse<Object> buildSuccess(String message){
        UserResponse<Object> response = new UserResponse<>();
        response.setCode(200);
        response.setMessage(message);
        return response;
    }

    public static UserResponse<Object> buildSuccess(Object result){
        UserResponse<Object> response = new UserResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setResult(result);
        return response;
    }

    public static UserResponse<Object> buildSuccess(String message,Object result){
        UserResponse<Object> response = new UserResponse<>();
        response.setCode(200);
        response.setMessage(message);
        response.setResult(result);
        return response;
    }

    public static UserResponse buildFaild(){
        UserResponse response = new UserResponse();
        response.setCode(103);
        response.setMessage("操作失败");
        return response;
    }

    public static UserResponse buildFaild(String message){
        UserResponse response = new UserResponse();
        response.setCode(103);
        response.setMessage(message);
        return response;
    }
}
