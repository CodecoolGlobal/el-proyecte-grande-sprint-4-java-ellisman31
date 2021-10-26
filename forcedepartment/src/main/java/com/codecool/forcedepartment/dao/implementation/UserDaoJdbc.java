package com.codecool.forcedepartment.dao.implementation;

import com.codecool.forcedepartment.dao.UserDao;


import javax.sql.DataSource;

public class UserDaoJdbc implements UserDao {

    private final DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
