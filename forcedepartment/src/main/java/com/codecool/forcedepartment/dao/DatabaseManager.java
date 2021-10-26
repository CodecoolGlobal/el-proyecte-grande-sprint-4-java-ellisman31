package com.codecool.forcedepartment.dao;

import com.codecool.forcedepartment.dao.implementation.CategoryDaoJdbc;
import com.codecool.forcedepartment.dao.implementation.UserDaoJdbc;
import com.codecool.forcedepartment.dao.implementation.WorkerDaoJdbc;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseManager {

    DatabaseConnection databaseConnection = new DatabaseConnection();

    private UserDao userDao;
    private WorkerDao workerDao;
    private CategoryDao categoryDao;

    public DatabaseManager() throws SQLException {
        DataSource dataSource = databaseConnection.connect();
        userDao = new UserDaoJdbc(dataSource);
        workerDao = new WorkerDaoJdbc(dataSource);
        categoryDao = new CategoryDaoJdbc(dataSource);

    }
}
