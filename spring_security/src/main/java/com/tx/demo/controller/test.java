package com.tx.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author tianxin
 * @Date 2019/1/30
 * @Description:
 */
@Controller
public class test {
    @RequestMapping("/rest/login")
    public String index(){
        return "login";
    }
    @RequestMapping("/logout")
    public String index2(){
        return "logout";
    }
}
