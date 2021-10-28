package com.codecool.forcedepartment.controller;

import com.codecool.forcedepartment.dao.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private DatabaseManager databaseManager;

    private static String webTitle = "Specialist department - Login";
    private static String websiteName = "Specialist department";

    @Autowired
    public LoginController(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @GetMapping
    public String loginSite(Model model) {
        model.addAttribute("websiteName", websiteName);
        model.addAttribute("title", webTitle);
        return "login";
    }

    @PostMapping
    public String loginToTheWebsite(@RequestParam("email") String email,
                                    @RequestParam("password") String password,
                                    HttpSession session) {

        if (databaseManager.checkValidLogin(email, password)) {
            session.setAttribute("email", email);
            return "redirect:/";
        }
        else {
            return "redirect:/login";
        }
    }

}
