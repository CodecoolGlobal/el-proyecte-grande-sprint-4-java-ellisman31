package com.codecool.forcedepartment.dao.implementation;

import com.codecool.forcedepartment.dao.UserDao;
import com.codecool.forcedepartment.model.User;


import javax.sql.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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

    @Override
    public void addNewRegularUser(User user, String hashedPassword) {
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
    public User getDataAboutUser(int id) {
        return null;
    }

    @Override
    public List<User> getAllDataAboutUser() {
        return null;
    }

    @Override
    public boolean checkIfUserExists(String email) {
        return false;
    }

    @Override
    public boolean checkIfValidLogin(String email, String password) {
        return false;
    }

    @Override
    public void editRegularProfile(String firstName, String lastName, String birthOfDate, String email) {

    }

    @Override
    public void editWorkerProfile(String firstName, String lastName, String birthOfDate, String email, String description, String phoneNumber, List<String> profession) {

    }
}
