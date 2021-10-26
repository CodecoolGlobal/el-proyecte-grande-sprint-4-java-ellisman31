package com.codecool.forcedepartment.model;

public class RegularUser extends User {

    private static final boolean IS_ADMIN = false;

    public RegularUser(String firstName, String lastName, int age, String phone_number, String groupName) {
        super(firstName, lastName, age, phone_number, IS_ADMIN, groupName);
    }

}
