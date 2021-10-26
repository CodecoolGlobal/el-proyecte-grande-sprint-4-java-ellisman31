package com.codecool.forcedepartment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {


    @GetMapping
    public String registerSite() {
        return "registration";
    }

    @PostMapping
    public void saveRegisterUserData() {
    }

}
