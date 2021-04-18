package com.sacp.web.controller;

import com.sacp.web.client.api.SacpWebApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CheckOkController {

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "Ok";
    }

}
