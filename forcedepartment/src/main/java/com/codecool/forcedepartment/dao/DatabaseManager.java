package com.codecool.forcedepartment.dao;

import com.codecool.forcedepartment.model.User;
import com.codecool.forcedepartment.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public int registerRegularUser(User user, String hashedPassword) { return userDao.addNewRegularUser(user, hashedPassword);}

    public void registerWorker(int workerId, String phoneNumber, String description) { userDao.addNewWorker(workerId, phoneNumber, description);}

    public User getAllDataAboutUser(int userId) { return userDao.getDataAboutUser(userId); }

    public boolean checkIfEmailInUse(String email) { return userDao.checkIfUserExists(email); }

}
