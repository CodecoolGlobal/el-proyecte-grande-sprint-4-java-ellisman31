package com.codecool.forcedepartment.service;

import com.codecool.forcedepartment.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    //Dummy, it will be with DAO

    private List<User> test = new ArrayList<>();

    public void addUserTest(User user) {
        test.add(user);
    }

    public void getAllInformation() {
        for (User user: test) {
            System.out.println("First name: " + user.getFirstName());
            System.out.println("Last name: " +user.getLastName());
            System.out.println("Email address: " +user.getEmail());
            System.out.println("Date of birth: " +user.getBirthOfDate());
            System.out.println("Password: " +user.getPassword());
            System.out.println("User type: " +user.getUserType());
        }
    }
}
