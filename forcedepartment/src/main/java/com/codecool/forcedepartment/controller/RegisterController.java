package com.codecool.forcedepartment.controller;

import com.codecool.forcedepartment.model.RegularUser;
import com.codecool.forcedepartment.model.User;
import com.codecool.forcedepartment.model.Worker;
import com.codecool.forcedepartment.model.util.GroupTypes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {


    @GetMapping
    public String registerSite(Model model) {

        model.addAttribute("User", new RegularUser());
        return "registration";

    }

    @PostMapping
    public String saveRegisterUserData(@ModelAttribute RegularUser user) {

        //Add to the DAO service
        if (user.getGroupType().equals(GroupTypes.WORKER)) {
            //Worker worker = new Worker();
            System.out.println(user.getGroupType());
        }
        else if (user.getGroupType().equals(GroupTypes.USER)) {
            System.out.println(user.getGroupType());
        }

        return "redirect:/register";

    }

}
