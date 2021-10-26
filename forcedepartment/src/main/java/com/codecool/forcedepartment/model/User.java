package com.codecool.forcedepartment.model;

import com.codecool.forcedepartment.model.util.UserTypes;

import java.time.LocalDateTime;

public abstract class User {

    private String firstName;
    private String lastName;
    //local date year and the given year
    private int age;
    private LocalDateTime registrationDate;
    private String birthOfDate;
    private String phone_number;
    private boolean isAdmin;
    private UserTypes userType;
    private String password;
    private String email;

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        //do regex to check the email
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthOfDate() {
        return birthOfDate;
    }

    public void setBirthOfDate(String birthOfDate) {
        this.birthOfDate = birthOfDate;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
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
