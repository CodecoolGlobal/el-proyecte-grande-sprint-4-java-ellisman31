package com.codecool.forcedepartment.dao.implementation;

import com.codecool.forcedepartment.dao.WorkerDao;
import com.codecool.forcedepartment.model.Profession;
import com.codecool.forcedepartment.model.WorkObject;
import com.codecool.forcedepartment.model.Worker;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkerDaoJdbc implements WorkerDao {

    private DataSource dataSource;

    public WorkerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Worker> getAllByRating() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT\n" +
                    "    worker.is_available,\n" +
                    "    worker.rate,\n" +
                    "    worker.description,\n" +
                    "    website_user.first_name,\n" +
                    "    website_user.last_name,\n" +
                    "    website_user.age,\n" +
                    "    website_user.phone_number,\n" +
                    "    website_user.is_admin,\n" +
                    "    website_user.group_name\n" +
                    "FROM worker FULL JOIN website_user ON worker.user_id = website_user.id WHERE website_user.group_name = 'worker' ORDER BY worker.rate DESC;";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Worker> result = new ArrayList<>();
            while (rs.next()) {
                Worker worker = new Worker(rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(3));
                //worker.setId(rs.getInt(1));
                result.add(worker);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
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
