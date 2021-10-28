package com.codecool.forcedepartment.model;

import com.codecool.forcedepartment.model.util.UserTypes;

import java.util.Date;

public class Admin extends User {

    public Admin(String firstName, String lastName, Date birthOfDate, String userType, String email) {
        super(firstName, lastName, birthOfDate, userType, email);
    }

    public Admin(int id, String firstName, String lastName, Date registrationDate, Date birthOfDate, String userType, String email) {
        super(id, firstName, lastName, registrationDate, birthOfDate, userType, email);
    }
}
