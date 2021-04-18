package com.sacp.admin.controller;

import com.sacp.web.client.api.SacpWebApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CheckOkController {

    @DubboReference(version = "1.0",check = false)
    private SacpWebApi sacpWebApi;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        sacpWebApi.getAllActive();
        return "Ok";
    }

}
