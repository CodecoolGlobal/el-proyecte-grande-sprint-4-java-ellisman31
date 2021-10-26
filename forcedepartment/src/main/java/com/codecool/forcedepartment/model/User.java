package com.codecool.forcedepartment.model;

import com.codecool.forcedepartment.model.util.GroupTypes;

public abstract class User {

    private String firstName;
    private String lastName;
    private int age;
    private String phone_number;
    private boolean isAdmin;
    private GroupTypes groupType;

    public GroupTypes getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupTypes groupType) {
        this.groupType = groupType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
