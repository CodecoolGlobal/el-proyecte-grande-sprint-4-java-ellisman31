package com.codecool.forcedepartment.controller;

import com.codecool.forcedepartment.model.User;
import com.codecool.forcedepartment.model.Worker;
import com.codecool.forcedepartment.model.util.UserTypes;
import com.codecool.forcedepartment.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping(value = "/register")
public class RegistrationController {

    //LocalDate because of registration date
    public static UserService userService = new UserService();
    private static String webTitle = "Specialist department - Registration";

    //Warning message if the match password is wrong
    @GetMapping
    public String registerSite(Model model) {

        model.addAttribute("title", webTitle);
        model.addAttribute("User", new User());
        model.addAttribute("UserTypes", Arrays.asList(UserTypes.values()));
        return "registration";

    }

    //Message for successful registration
    @PostMapping
    public String saveRegisterUserData(@RequestParam("password") String password,
                                       @RequestParam("passwordAgain") String passwordAgain,
                                       @ModelAttribute User user) {

        //Add to the DAO service
        //Test with dummy
        if (password.equals(passwordAgain)) {
            if (user.getUserType().equals(UserTypes.WORKER)) {
                //set to saveRemainingData
                List<String> dummy = new ArrayList<>();
                Worker worker = new Worker(
                        user.getFirstName(), user.getLastName(), "registration date", user.getBirthOfDate(),
                        user.getUserType(), user.getEmail(), "", "", dummy);
                userService.addUserTest(worker);
            }
            else if (user.getUserType().equals(UserTypes.USER)) {
                userService.addUserTest(user);
            }
            userService.getAllInformation();
        }
        else {
            return "redirect:/register";
        }
        return "redirect:/";
    }

    @GetMapping("/register/worker")
    public String remainingDataForWorker() {
        return "register-worker";
    }

    @PostMapping("/register/worker")
    public String saveRemainingDataForWorker() {
        return "redirect:/";
    }
}