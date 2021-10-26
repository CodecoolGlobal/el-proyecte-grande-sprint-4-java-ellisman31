package com.codecool.forcedepartment.model;

public class Admin extends User {

    private static final boolean IS_ADMIN = true;

    public Admin(String firstName, String lastName, int age, String phone_number, String groupName) {
        super(firstName, lastName, age, phone_number, IS_ADMIN, groupName);
    }

}
