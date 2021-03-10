package com.sacp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CheckOkController {

    @RequestMapping("/login")
    public ModelAndView checkOk(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("zq","周庆");
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
