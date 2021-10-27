package com.codecool.forcedepartment.dao.implementation;

import com.codecool.forcedepartment.dao.WorkerDao;
import com.codecool.forcedepartment.model.Worker;
import com.codecool.forcedepartment.model.util.UserTypes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkerDaoJdbc implements WorkerDao {

    private DataSource dataSource;
    private String workerText = String.valueOf(UserTypes.WORKER);

    public WorkerDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private List<String> arrayAggConverter(String string) {
        StringBuilder arrayAgg = new StringBuilder("{Sheet Metal Worker,Plumber}");
        arrayAgg.deleteCharAt(arrayAgg.length() - 1);
        arrayAgg.deleteCharAt(0);
        String planeArray = arrayAgg.toString();
        List<String> listOfProfession = Arrays.asList(planeArray.split("\s,\s"));
        return listOfProfession;
    }

    private List<Worker> getWorkers(PreparedStatement st) throws SQLException {
        ResultSet rs = st.executeQuery();
        List<Worker> result = new ArrayList<>();
        while (rs.next()) {
            List<String> listOfProfession = arrayAggConverter(rs.getString(10));
            Worker worker = new Worker(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7),
                    rs.getString(8), rs.getString(9), listOfProfession, rs.getDouble(10));
            result.add(worker);
        }
        return result;
    }

    @Override
    public List<Worker> getAllByRating() {
        try (Connection conn = dataSource.getConnection()) {
        String sql = "SELECT\n" +
                "   website_user.first_name,\n" +
                "   website_user.last_name,\n" +
                "   website_user.registration_date,\n" +
                "   website_user.birth_date,\n" +
                "   website_user.is_admin,\n" +
                "   website_user.group_name,\n" +
                "   website_user.email,\n" +
                "   worker.description,\n" +
                "    worker.phone_number,\n" +
                "    worker.rate,\n" +
                "    ARRAY_AGG(profession.profession_name)\n" +
                "FROM worker\n" +
                "   FULL JOIN website_user ON worker.user_id = website_user.id\n" +
                "   FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                "   FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                "WHERE website_user.group_name = ?\n" +
                "GROUP BY website_user.first_name, website_user.last_name, website_user.registration_date, website_user.birth_date, website_user.is_admin, website_user.group_name, website_user.email, worker.description, worker.phone_number, worker.rate\n" +
                "ORDER BY worker.rate DESC;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, workerText);
            ResultSet rs = st.executeQuery();
            List<Worker> result = new ArrayList<>();
            while (rs.next()) {
                List<String> listOfProfession = arrayAggConverter(rs.getString(11));
                Worker worker = new Worker(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), listOfProfession, rs.getDouble(10));
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
                    "   website_user.first_name,\n" +
                    "   website_user.last_name,\n" +
                    "   website_user.registration_date,\n" +
                    "   website_user.birth_date,\n" +
                    "   website_user.is_admin,\n" +
                    "   website_user.group_name,\n" +
                    "   website_user.email,\n" +
                    "   worker.description,\n" +
                    "    worker.phone_number,\n" +
                    "    worker.rate,\n" +
                    "    ARRAY_AGG(profession.profession_name)\n" +
                    "FROM worker\n" +
                    "   FULL JOIN website_user ON worker.user_id = website_user.id\n" +
                    "   FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                    "   FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "WHERE website_user.group_name = ? AND profession.profession_name = ?\n" +
                    "GROUP BY website_user.first_name, website_user.last_name, website_user.registration_date, website_user.birth_date, website_user.is_admin, website_user.group_name, website_user.email, worker.description, worker.phone_number, worker.rate;\n";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, workerText);
            st.setString(2, profession);
            return getWorkers(st);
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }

    @Override
    public List<Worker> getAllByWorkObject(String workObject) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT\n" +
                    "   website_user.first_name,\n" +
                    "   website_user.last_name,\n" +
                    "   website_user.registration_date,\n" +
                    "   website_user.birth_date,\n" +
                    "   website_user.is_admin,\n" +
                    "   website_user.group_name,\n" +
                    "   website_user.email,\n" +
                    "   worker.description,\n" +
                    "    worker.phone_number,\n" +
                    "    worker.rate,\n" +
                    "    ARRAY_AGG(profession.profession_name)\n" +
                    "FROM worker\n" +
                    "   FULL JOIN website_user ON worker.user_id = website_user.id\n" +
                    "   FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                    "   FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "   FULL JOIN work_requirement ON profession.id = work_requirement.profession_id\n" +
                    "   FULL JOIN work_object ON work_object.id = work_requirement.work_object_id\n" +
                    "WHERE website_user.group_name = ? AND work_object.work_object = ?\n" +
                    "GROUP BY website_user.first_name, website_user.last_name, website_user.registration_date, website_user.birth_date, website_user.is_admin, website_user.group_name, website_user.email, worker.description, worker.phone_number, worker.rate;\n";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, workerText);
            st.setString(2, workObject);
            return getWorkers(st);
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }

    @Override
    public List<Worker> getAllByName(String name) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT\n" +
                    "   website_user.first_name,\n" +
                    "   website_user.last_name,\n" +
                    "   website_user.registration_date,\n" +
                    "   website_user.birth_date,\n" +
                    "   website_user.is_admin,\n" +
                    "   website_user.group_name,\n" +
                    "   website_user.email,\n" +
                    "   worker.description,\n" +
                    "    worker.phone_number,\n" +
                    "    worker.rate,\n" +
                    "    ARRAY_AGG(profession.profession_name)\n" +
                    "FROM worker\n" +
                    "   FULL JOIN website_user ON worker.user_id = website_user.id\n" +
                    "   FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                    "   FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "WHERE website_user.group_name = ? AND  LOWER(website_user.first_name) LIKE LOWER(?) OR LOWER(website_user.last_name) LIKE LOWER(?)\n" +
                    "GROUP BY website_user.first_name, website_user.last_name, website_user.registration_date, website_user.birth_date, website_user.is_admin, website_user.group_name, website_user.email, worker.description, worker.phone_number, worker.rate;\n";
            PreparedStatement st = conn.prepareStatement(sql);
            String namePart = "%" + name + "%";
            st.setString(1, workerText);
            st.setString(2, namePart);
            st.setString(3, namePart);
            return getWorkers(st);
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
