package com.codecool.forcedepartment.controller;

import com.codecool.forcedepartment.dao.DatabaseManager;
import com.codecool.forcedepartment.model.Profession;
import com.codecool.forcedepartment.model.User;
import com.codecool.forcedepartment.model.util.UserTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


@Controller
public class RegistrationController {

    private DatabaseManager databaseManager;

    private static String webTitle = "Specialist department - Registration";
    private static int workerId;

    @Autowired
    public RegistrationController(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    private Date dateConverter(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date actualDate = new Date();
        return dateFormat.parse(String.valueOf(actualDate));
    }

    //Warning message if the match password is wrong
    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String registerSite(Model model) {

        model.addAttribute("title", webTitle);
        model.addAttribute("User", new User());
        model.addAttribute("UserTypes", Arrays.asList(UserTypes.values()));
        return "registration";

    }

    //Message for successful registration
    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    public String saveRegisterUserData(@RequestParam("password") String password,
                                       @RequestParam("passwordAgain") String passwordAgain,
                                       @ModelAttribute User user) throws ParseException {

        if (password.equals(passwordAgain)) {
            if (user.getUserType().equals(String.valueOf(UserTypes.WORKER))) { ;
                User worker = new User(
                        user.getFirstName(), user.getLastName(), user.getBirthOfDate(),
                        user.getUserType(), user.getEmail());
                workerId = databaseManager.registerRegularUser(user, user.getPassword());
                return "redirect:/register/worker";
            } else if (user.getUserType().equals(String.valueOf(UserTypes.USER))) {
                databaseManager.registerRegularUser(user, user.getPassword());
            }
        } else {
            return "redirect:/register";
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/register/worker", method = {RequestMethod.GET})
    public String remainingDataForWorker(Model model) {

        model.addAttribute("title", webTitle);
        model.addAttribute("ProfessionObject", new Profession());
        model.addAttribute("professions", databaseManager.getAllProfession());

        return "register-worker";
    }


    //@RequestParam("professionName") String workerProfession
    @RequestMapping(value = "/register/worker", method = {RequestMethod.POST})
    public String saveRemainingDataForWorker(
            @RequestParam("description") String description,
            @RequestParam("phone_number") String phoneNumber
    ) {

        databaseManager.registerWorker(workerId, description, phoneNumber);

        return "redirect:/";
    }
}