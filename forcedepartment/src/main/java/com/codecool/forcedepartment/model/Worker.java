package com.codecool.forcedepartment.model;

public class Worker extends User {

    private static final boolean IS_ADMIN = false;
    private String description;
    private double rate;

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
