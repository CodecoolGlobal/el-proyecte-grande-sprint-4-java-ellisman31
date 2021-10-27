package com.codecool.forcedepartment.model;

import com.codecool.forcedepartment.model.util.UserTypes;

import java.time.LocalDateTime;

public class User {

    private String firstName;
    private String lastName;
    //local date year and the given year
    private int age;
    private String registrationDate;
    private String birthOfDate;
    private boolean isAdmin;
    private UserTypes userType;
    private String password;
    private String email;
    //private String profileImage;

    public User() {
    }

    public User(String firstName, String lastName, String registrationDate, String birthOfDate, boolean isAdmin, UserTypes userType, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.birthOfDate = birthOfDate;
        this.isAdmin = isAdmin;
        this.userType = userType;
        this.password = password;
        this.email = email;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", registrationDate='" + registrationDate + '\'' +
                ", birthOfDate='" + birthOfDate + '\'' +
                ", isAdmin=" + isAdmin +
                ", userType=" + userType +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
