package com.sacp.web.response;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UnauthenticatedException.class})
    public UserResponse unLoginHandler(){
        UserResponse userResponse = new UserResponse();
        userResponse.setCode(301);
        Subject subject = SecurityUtils.getSubject();
        userResponse.setToken(subject.getSession().getId().toString());
        userResponse.setMessage("未登录或登录身份失效，请登录！");
        userResponse.setStatus("faild");
        userResponse.setResult("请登录");
        return userResponse;
    }
}
