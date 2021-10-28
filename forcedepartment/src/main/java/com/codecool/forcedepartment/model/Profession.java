package com.codecool.forcedepartment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Profession {

    //private Map<String, Double> professions = new HashMap<>();
    private List<String> professions = new ArrayList<>();
    private String professionName;
    private double experience_year;

    public Profession() {
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public double getExperience_year() {
        return experience_year;
    }

    public void setExperience_year(double experience_year) {
        this.experience_year = experience_year;
    }

    public void addProfession(String profession) {
        professions.add(profession);
    }

    public List<String> getProfessions() {
        return professions;
    }

    public String getProfessionName() {
        return professionName;
    }
}
