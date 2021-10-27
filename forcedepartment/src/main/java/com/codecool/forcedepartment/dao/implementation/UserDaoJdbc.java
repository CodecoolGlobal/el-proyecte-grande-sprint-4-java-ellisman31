package com.codecool.forcedepartment.dao.implementation;

import com.codecool.forcedepartment.dao.UserDao;
import com.codecool.forcedepartment.model.User;
import com.codecool.forcedepartment.model.Worker;


import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserDaoJdbc implements UserDao {

    private final DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public int getLatestId(String tableName) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT MAX(id) FROM " + tableName;
            ResultSet rs = conn.createStatement().executeQuery(sql);
            if (!rs.next()) {
                return 1;
            }
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCurrentRegistrationDate() {
        String PATTERN="yyyy-MM-dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat();
        dateFormat.applyPattern(PATTERN);
        return dateFormat.format(Calendar.getInstance().getTime());
    }


    // WHEN EDITING A PROFILE THIS COULD BE USED - TWO DIFFERENT METHODS ARE FOR USERS AND WORKERS
    public String getGroupTypeByUserId(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT website_user.group_name\n" +
                    "        FROM website_user\n" +
                    "        WHERE website_user.id = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return rs.getString(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading user with email  . Error type: ", e);
        }
    }

    @Override
    public int addNewRegularUser(User user, String hashedPassword) {
        try (Connection conn = dataSource.getConnection()) {
            String registrationDate = getCurrentRegistrationDate();
            String sql = "INSERT INTO website_user (first_name, last_name, birth_date, email, is_admin, password, registration_date, group_name)\n" +
                     "            VALUES (?, ?, ?, ?, ?, ?, ?, ?);\n";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getFirstName());
            st.setString(2, user.getLastName());
            st.setString(3, user.getBirthOfDate());
            st.setString(4, user.getEmail());
            st.setBoolean(5, user.isAdmin());
            st.setString(6, hashedPassword);
            st.setString(7, registrationDate);
            st.setString(8, user.getUserType());
            st.executeUpdate();
            return getLatestId("website_user");
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding user Error type: ", e);
        }
    }

    @Override
    public void addNewWorker(int workerId, String phoneNumber, String description) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO worker (user_id, phone_number, is_available, rate, description)\n" +
                            "VALUES (?, ?, false, 0.0, ?);";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, workerId);
            st.setString(2, phoneNumber);
            st.setString(3, description);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding user Error type: ", e);
        }
    }

    @Override
    public User getDataAboutUser(int userId) {
        String userType = getGroupTypeByUserId(userId);
        String sql = "";
        if (userType.equals("USER")) {
            sql = "SELECT\n" +
                    "       website_user.first_name,\n" +
                    "       website_user.last_name,\n" +
                    "       website_user.birth_date,\n" +
                    "       website_user.email,\n" +
                    "       website_user.is_admin,\n" +
                    "       website_user.registration_date\n" +
                    "FROM website_user\n" +
                    "WHERE website_user.id = ?;";
        } else {
            sql = "SELECT\n" +
                    "       website_user.first_name,\n" +
                    "       website_user.last_name,\n" +
                    "       website_user.birth_date,\n" +
                    "       website_user.email,\n" +
                    "       website_user.is_admin,\n" +
                    "       website_user.registration_date,\n" +
                    "       worker.description,\n" +
                    "       worker.phone_number,\n" +
                    "       ARRAY_AGG(profession.profession_name),\n" +
                    "       worker.rate\n" +
                    "FROM website_user\n" +
                    "FULL JOIN worker ON website_user.id = worker.user_id\n" +
                    "FULL JOIN worker_experience ON website_user.id = worker_experience.worker_id\n" +
                    "FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "WHERE website_user.id = ?\n" +
                    "GROUP BY website_user.first_name, website_user.last_name, website_user.birth_date, website_user.email, website_user.is_admin, website_user.registration_date, worker.description, worker.phone_number, worker.rate;";
        }
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            if (userType.equals("USER")) {
                return new User(rs.getString(1), rs.getString(2), rs.getString(6), rs.getString(3), rs.getBoolean(5), userType, rs.getString(4));
            } else {
                List<String> workerProfessions = WorkerDaoJdbc.arrayAggConverter(rs.getString(9));
                System.out.println(new Worker(rs.getString(1), rs.getString(2), rs.getString(6), rs.getString(3), userType, rs.getString(4), rs.getString(7), rs.getString(8), workerProfessions, rs.getDouble(10)));
                return new Worker(rs.getString(1), rs.getString(2), rs.getString(6), rs.getString(3), userType, rs.getString(4), rs.getString(7), rs.getString(8), workerProfessions, rs.getDouble(10));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading user with email  . Error type: ", e);
        }
    }

    @Override
    public List<User> getAllDataAboutUser() {
        return null;
    }

    @Override
    public boolean checkIfUserExists(String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT website_user.id\n" +
                    "FROM website_user\n" +
                    "WHERE email = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading user with email  . Error type: ", e);
        }
    }

    @Override
    public boolean checkIfValidLogin(String email, String password) {

        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT website_user.id\n" +
                    "FROM website_user\n" +
                    "WHERE email = ? AND password = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading user with email  . Error type: ", e);
        }
    }



    @Override
    public void editRegularProfile(int userId, String firstName, String lastName, String birthOfDate, String email, String password) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE website_user\n" +
                    "SET first_name = ?, last_name = ?, birth_date = ?, email = ?, password = ?\n" +
                    "WHERE website_user.id = ?;";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, firstName);
            st.setString(2, lastName);
            st.setString(3, birthOfDate);
            st.setString(4, email);
            st.setString(5, password);
            st.setInt(6, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding user Error type: ", e);
        }

    }


    @Override
    public void editWorkerProfile(int userId, String firstName, String lastName, String birthOfDate, String email,
                                  String password, String description, String phoneNumber, boolean isAvailable)
    {
        try (Connection conn = dataSource.getConnection()) {
            editRegularProfile(userId, firstName, lastName, birthOfDate, email, password);
            String sql = "    UPDATE worker\n" +
                    "    SET phone_number = ?, is_available = ?, description = ?\n" +
                    "    WHERE worker.id = ?;";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, phoneNumber);
            st.setBoolean(2, isAvailable);
            st.setString(3, description);
            st.setInt(4, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while adding user Error type: ", e);
        }
    }


    @Override
    public Map<String, Integer> getProfessionWithExperience(int userId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT worker_experience.experience_years,\n" +
                    "       profession.profession_name\n" +
                    "FROM worker_experience\n" +
                    "FULL JOIN profession ON profession.id = worker_experience.profession_id\n" +
                    "FULL JOIN website_user ON website_user.id = worker_experience.worker_id\n" +
                    "WHERE website_user.id = ?;";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            Map<String, Integer> professionWithExperience = new HashMap<>();
            while (rs.next()) {
                professionWithExperience.put(rs.getString(2), rs.getInt(1));
            }
            return professionWithExperience;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading user with email  . Error type: ", e);
        }
    }


}
