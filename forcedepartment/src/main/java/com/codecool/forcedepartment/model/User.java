package com.codecool.forcedepartment.model;

import com.codecool.forcedepartment.model.util.UserTypes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class User {

    private String firstName;
    private String lastName;
    private int age;
    private String registrationDate;
    private String birthOfDate;
    private boolean isAdmin;
    private String userType;
    private String password;
    private String email;
    private String image = "profile-icon-empty.png";
    private String imageName;
    private static final boolean IS_ADMIN = false;
    //private String profileImage;

    public User() {
    }

    public User(String firstName, String lastName, String registrationDate, String birthOfDate, String userType, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationDate = registrationDate;
        this.birthOfDate = birthOfDate;
        this.isAdmin = IS_ADMIN;
        this.userType = userType;
        this.email = email;
    }

    public String getImageName() {
        return firstName + " " + lastName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAge() {

        Date date = new Date();

        SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
        String currentYear = getYearFormat.format(date);
        String birthYear = getBirthOfDate().split("-")[0];

        return Integer.parseInt(currentYear) - Integer.parseInt(birthYear);
    }


    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
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
                ", email='" + email + '\'' +
                "}\n";
    }
}
