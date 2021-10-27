package com.codecool.forcedepartment.model;

import com.codecool.forcedepartment.model.util.UserTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Worker extends User {

    private static final boolean IS_ADMIN = false;
    private String description;
    private String phoneNumber;
    private double rate;
    private List<String> profession;

    public Worker(String firstName, String lastName, String registrationDate, String birthOfDate, UserTypes userType, String password, String email, String description, String phoneNumber, List<String> profession) {
        super(firstName, lastName, registrationDate, birthOfDate, IS_ADMIN, userType, password, email);
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.profession = new ArrayList<>();
        this.profession = profession;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getProfession() {
        return profession;
    }

    public void setProfession(List<String> profession) {
        this.profession = profession;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
