package com.codecool.forcedepartment.dao.implementation;

import com.codecool.forcedepartment.dao.WorkerDao;
import com.codecool.forcedepartment.model.Worker;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
                    "    website_user.first_name,\n" +
                    "    website_user.last_name,\n" +
                    "    website_user.age,\n" +
                    "    website_user.phone_number,\n" +
                    "    website_user.is_admin,\n" +
                    "    website_user.group_name,\n" +
                    "    worker.is_available,\n" +
                    "    worker.rate,\n" +
                    "    worker.description,\n" +
                    "    ARRAY_AGG(profession.profession_name)\n" +
                    "FROM worker\n" +
                    "    FULL JOIN website_user ON worker.user_id = website_user.id\n" +
                    "    FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                    "    FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "WHERE website_user.group_name = 'worker'\n" +
                    "GROUP BY website_user.first_name, website_user.last_name, website_user.age, website_user.phone_number, website_user.is_admin, website_user.group_name, worker.is_available, worker.rate, worker.description;";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Worker> result = new ArrayList<>();
            while (rs.next()) {
                Worker worker = new Worker(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(9));
                result.add(worker);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }

    @Override
    public List<Worker> getAllByProfession(String profession) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT\n" +
                    "    website_user.first_name,\n" +
                    "    website_user.last_name,\n" +
                    "    website_user.age,\n" +
                    "    website_user.phone_number,\n" +
                    "    website_user.is_admin,\n" +
                    "    website_user.group_name,\n" +
                    "    worker.is_available,\n" +
                    "    worker.rate,\n" +
                    "    worker.description,\n" +
                    "    ARRAY_AGG(profession.profession_name)\n" +
                    "FROM worker\n" +
                    "    FULL JOIN website_user ON worker.user_id = website_user.id\n" +
                    "    FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                    "    FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "WHERE website_user.group_name = 'worker' AND profession.profession_name = ?\n" +
                    "GROUP BY website_user.first_name, website_user.last_name, website_user.age, website_user.phone_number, website_user.is_admin, website_user.group_name, worker.is_available, worker.rate, worker.description;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, profession);
            ResultSet rs = st.executeQuery();
            List<Worker> result = new ArrayList<>();
            while (rs.next()) {
                Worker worker = new Worker(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(9));
                result.add(worker);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }

    @Override
    public List<Worker> getAllByWorkObject(String workObject) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT\n" +
                    "    website_user.first_name,\n" +
                    "    website_user.last_name,\n" +
                    "    website_user.age,\n" +
                    "    website_user.phone_number,\n" +
                    "    website_user.is_admin,\n" +
                    "    website_user.group_name,\n" +
                    "    worker.is_available,\n" +
                    "    worker.rate,\n" +
                    "    worker.description,\n" +
                    "    ARRAY_AGG(profession.profession_name)\n" +
                    "FROM worker\n" +
                    "    FULL JOIN website_user ON worker.user_id = website_user.id\n" +
                    "    FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                    "    FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "    FULL JOIN work_requirement ON profession.id = work_requirement.profession_id\n" +
                    "    FULL JOIN work_object ON work_object.id = work_requirement.work_object_id\n" +
                    "WHERE website_user.group_name = 'worker' AND work_object.work_object = ?\n" +
                    "GROUP BY website_user.first_name, website_user.last_name, website_user.age, website_user.phone_number, website_user.is_admin, website_user.group_name, worker.is_available, worker.rate, worker.description;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, workObject);
            ResultSet rs = st.executeQuery();
            List<Worker> result = new ArrayList<>();
            while (rs.next()) {
                Worker worker = new Worker(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(9));
                result.add(worker);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }

    @Override
    public List<Worker> getAllByName(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT\n" +
                    "    website_user.first_name,\n" +
                    "    website_user.last_name,\n" +
                    "    website_user.age,\n" +
                    "    website_user.phone_number,\n" +
                    "    website_user.is_admin,\n" +
                    "    website_user.group_name,\n" +
                    "    worker.is_available,\n" +
                    "    worker.rate,\n" +
                    "    worker.description,\n" +
                    "    ARRAY_AGG(profession.profession_name)\n" +
                    "FROM worker\n" +
                    "    FULL JOIN website_user ON worker.user_id = website_user.id\n" +
                    "    FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                    "    FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "WHERE website_user.group_name = 'worker' AND website_user.first_name LIKE ? OR website_user.last_name LIKE ?\n" +
                    "GROUP BY website_user.first_name, website_user.last_name, website_user.age, website_user.phone_number, website_user.is_admin, website_user.group_name, worker.is_available, worker.rate, worker.description;";
            PreparedStatement st = conn.prepareStatement(sql);
            String namePart = "%" + name + "%";
            st.setString(1, namePart);
            st.setString(2, namePart);
            ResultSet rs = st.executeQuery();
            List<Worker> result = new ArrayList<>();
            while (rs.next()) {
                Worker worker = new Worker(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(9));
                result.add(worker);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }

    @Override
    public List<Worker> getAllByFilter() {

        // work-object, worker-name, profession, rate, is-available, year experience

        return null;
    }
}
