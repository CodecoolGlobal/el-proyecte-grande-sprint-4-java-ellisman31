package com.codecool.forcedepartment.dao.implementation;

import com.codecool.forcedepartment.dao.CategoryDao;

import javax.sql.DataSource;

public class CategoryDaoJdbc implements CategoryDao {

    private DataSource dataSource;

    public CategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
