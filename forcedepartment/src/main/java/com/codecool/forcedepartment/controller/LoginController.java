package com.codecool.forcedepartment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.codecool.forcedepartment.controller.RegistrationController.userService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private static String webTitle = "Specialist department - Login";
    private static String websiteName = "Specialist department";

    @GetMapping
    public String loginSite(Model model) {
        model.addAttribute("websiteName", websiteName);
        model.addAttribute("title", webTitle);
        return "login";
    }

    @PostMapping
    public String loginToTheWebsite(@RequestParam("email") String email,
                                    @RequestParam("password") String password) {

        userService.checkUserByEmailAndPassword(email, password);
        if (userService.isUserIsExist()) {
            System.out.println(email + " and " + password + " were correct!");
            return "redirect:/";
        }
        else {
            return "redirect:/login";
        }

    }

}
