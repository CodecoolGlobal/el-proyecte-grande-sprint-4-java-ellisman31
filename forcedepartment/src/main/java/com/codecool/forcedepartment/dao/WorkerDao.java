package com.codecool.forcedepartment.dao;

import com.codecool.forcedepartment.model.Worker;

import java.util.List;

public interface WorkerDao {

    List<Worker> getAllByRating();
    List<Worker> getAllByProfession(String profession);
    List<Worker> getAllByWorkObject(String workObject);
    List<Worker> getAllByName(String name);
    List<Worker> getAllByFilter();



}
