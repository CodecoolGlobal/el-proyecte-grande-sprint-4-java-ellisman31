package com.codecool.forcedepartment.dao;

import com.codecool.forcedepartment.dao.implementation.CategoryDaoJdbc;
import com.codecool.forcedepartment.dao.implementation.UserDaoJdbc;
import com.codecool.forcedepartment.dao.implementation.WorkerDaoJdbc;
import com.codecool.forcedepartment.model.WorkObject;
import com.codecool.forcedepartment.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@Component
public class DatabaseManager {

    private UserDao userDao;
    private WorkerDao workerDao;
    private CategoryDao categoryDao;

    @Autowired
    public DatabaseManager(UserDao userDao, WorkerDao workerDao, CategoryDao categoryDao) {
        this.userDao = userDao;
        this.workerDao = workerDao;
        this.categoryDao = categoryDao;
    }

    public List<Worker> getWorkersByRating() {
        return workerDao.getAllByRating();
    }

    public List<Worker> getWorkersByProfession(String profession) {
        return workerDao.getAllByProfession(profession);
    }

    public List<Worker> getWorkersByWorkObject(String workObject) {
        return workerDao.getAllByWorkObject(workObject);
    }

    public List<Worker> getWorkersByNamePart(String namePart) {
        return workerDao.getAllByName(namePart);
    }

    public List<String> getAllProfession() { return categoryDao.getAllProfession(); }

    public List<String> getAllWorkObject() { return categoryDao.getAllWorkObject(); }

}
