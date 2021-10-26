package com.codecool.forcedepartment.model;

public abstract class User {

    private String firstName;
    private String lastName;
    private int age;
    private String phone_number;
    private boolean isAdmin;
    private String groupName;

    public User(String firstName, String lastName, int age, String phone_number, boolean isAdmin, String groupName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone_number = phone_number;
        this.isAdmin = isAdmin;
        this.groupName = groupName;
    }
}
