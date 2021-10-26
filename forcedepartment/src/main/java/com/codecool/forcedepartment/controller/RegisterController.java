package com.codecool.forcedepartment.controller;

import com.codecool.forcedepartment.model.RegularUser;
import com.codecool.forcedepartment.model.util.UserTypes;
import com.codecool.forcedepartment.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    private static UserService userService = new UserService();

    //Warning message if the match password is wrong
    @GetMapping
    public String registerSite(Model model) {

        model.addAttribute("User", new RegularUser());
        return "registration";

    }

    //Message for successful registration
    @PostMapping
    public String saveRegisterUserData(@RequestParam("password") String password,
                                       @RequestParam("passwordAgain") String passwordAgain,
                                       @ModelAttribute RegularUser user) {

        //Add to the DAO service
        //Test with dummy
        if (password.equals(passwordAgain)) {
            if (user.getUserType().equals(UserTypes.WORKER)) {
                //Worker worker = new Worker();
                userService.addUserTest(user);
            }
            else if (user.getUserType().equals(UserTypes.USER)) {
                userService.addUserTest(user);
            }
            userService.getAllInformation();
        }
        else {
            return "redirect:/register";
        }


        return "redirect:/register";

    }
}