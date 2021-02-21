package com.sacp.member.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("checkok")
    public String checkOk(){
        return "OK";
    }
}
