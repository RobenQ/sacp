package com.sacp.web.controller;

import com.sacp.web.util.QiNiuUploadUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("upload")
public class UploadController {

    @RequiresAuthentication
    @RequestMapping("avatar")
    public String getToken(){
        String token = QiNiuUploadUtil.getDefaultToken();
        return token;
    }
}
