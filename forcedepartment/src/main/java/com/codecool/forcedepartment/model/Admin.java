package com.codecool.forcedepartment.model;

import com.codecool.forcedepartment.model.util.UserTypes;

public class Admin extends User {

    private static final boolean IS_ADMIN = true;

    public Admin(String firstName, String lastName, String registrationDate, String birthOfDate, UserTypes userType, String password, String email) {
        super(firstName, lastName, registrationDate, birthOfDate, IS_ADMIN, userType, password, email);
    }
}
