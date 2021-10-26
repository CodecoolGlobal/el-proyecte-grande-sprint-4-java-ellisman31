package com.codecool.forcedepartment.model;

public class Worker extends User {

    private static final boolean IS_ADMIN = false;
    private String description;
    private double rate;

    public Worker(String firstName, String lastName, int age, String phone_number, String groupName) {
        super(firstName, lastName, age, phone_number, IS_ADMIN, groupName);
    }
}
