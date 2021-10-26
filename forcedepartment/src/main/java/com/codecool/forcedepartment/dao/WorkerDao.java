package com.codecool.forcedepartment.dao;

import com.codecool.forcedepartment.model.Profession;
import com.codecool.forcedepartment.model.WorkObject;
import com.codecool.forcedepartment.model.Worker;

import java.util.List;

public interface WorkerDao {

    List<Worker> getAllByRating();
    List<Worker> getAllByProfession(Profession profession);
    List<Worker> getAllByWorkObject(WorkObject workObject);
    List<Worker> getAllByName(String name);
    List<Worker> getAllByFilter();

}
