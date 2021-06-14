package com.sacp.admin.response;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AuthorizationException.class})
    public AdminResponse noRole(){
        AdminResponse response = new AdminResponse();
        response.setMessage("你没有管理员权限，无法进入管理后台！");
        response.setCode(204);
        return response;
    }
}
