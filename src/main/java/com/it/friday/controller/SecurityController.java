package com.it.friday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author : Brave
 * @Version : 1.0
 * @Date : 2020/2/20 14:10
 */
@Controller
public class SecurityController {

    @GetMapping(value = "/login.html")
    public String login(){
        return "login";
    }

    @GetMapping(value = "/403.html")
    public String noPermission(){
        return "403";
    }
}
