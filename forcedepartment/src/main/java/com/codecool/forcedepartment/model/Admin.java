package com.codecool.forcedepartment.model;

import com.codecool.forcedepartment.model.util.UserTypes;

public class Admin extends User {

    public Admin(String firstName, String lastName, String registrationDate, String birthOfDate, String userType, String email) {
        super(firstName, lastName, registrationDate, birthOfDate, userType, email);
    }
}
