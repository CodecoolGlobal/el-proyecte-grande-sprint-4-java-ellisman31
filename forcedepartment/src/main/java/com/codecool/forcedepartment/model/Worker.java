package com.codecool.forcedepartment.model;

public class Worker extends User {

    private String first_name;
    private String last_name;
    private int age;
    private String description;

    public Worker(String first_name, String last_name, int age, String description) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                '}';
    }
}
