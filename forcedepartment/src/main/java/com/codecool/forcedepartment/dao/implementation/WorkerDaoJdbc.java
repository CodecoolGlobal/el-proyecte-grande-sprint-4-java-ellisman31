package com.codecool.forcedepartment.dao.implementation;

import com.codecool.forcedepartment.dao.WorkerDao;
import com.codecool.forcedepartment.model.Profession;
import com.codecool.forcedepartment.model.WorkObject;
import com.codecool.forcedepartment.model.Worker;

import javax.sql.DataSource;
import java.util.List;

public class WorkerDaoJdbc implements WorkerDao {



    public WorkerDaoJdbc(DataSource dataSource) {
    }

    @Override
    public List<Worker> getAllByRating() {
        return null;
    }

    @Override
    public List<Worker> getAllByProfession(Profession profession) {
        return null;
    }

    @Override
    public List<Worker> getAllByWorkObject(WorkObject workObject) {
        return null;
    }

    @Override
    public List<Worker> getAllByName(String name) {
        return null;
    }

    @Override
    public List<Worker> getAllByFilter() {
        return null;
    }
}
