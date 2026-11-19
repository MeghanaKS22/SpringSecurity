package com.SpringSecurity.SpringSecurity.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class demoController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
}
