package com.sacp.admin.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AdminResponse<T> implements Serializable {
    private int code;
    private String token;
    private String sacpId;
    private String avatar;
    private String introduction;
    private List<String> roles;
    private String message;
    private String status;
    private T result;

    public static AdminResponse<Object> buildSuccess(){
        AdminResponse<Object> response = new AdminResponse<>();
        response.setCode(200);
        response.setMessage("success");
        return response;
    }

    public static AdminResponse<Object> buildSuccess(Object result){
        AdminResponse<Object> response = new AdminResponse<>();
        response.setCode(200);
        response.setMessage("success");
        response.setResult(result);
        return response;
    }

    public static AdminResponse<Object> buildSuccess(String message,Object result){
        AdminResponse<Object> response = new AdminResponse<>();
        response.setCode(200);
        response.setMessage(message);
        response.setResult(result);
        return response;
    }

    public static AdminResponse buildFaild(){
        AdminResponse response = new AdminResponse();
        response.setCode(103);
        response.setMessage("操作失败");
        return response;
    }

    public static AdminResponse buildFaild(String message){
        AdminResponse response = new AdminResponse();
        response.setCode(103);
        response.setMessage(message);
        return response;
    }
}
