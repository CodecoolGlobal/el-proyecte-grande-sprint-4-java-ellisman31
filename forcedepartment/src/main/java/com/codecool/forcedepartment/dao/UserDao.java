package com.codecool.forcedepartment.dao;

import com.codecool.forcedepartment.model.User;

import java.util.List;

public interface UserDao {

    // add new regular user

    int addNewRegularUser(User user, String hashedPassword);

    // add new worker

    void addNewWorker(int workerId, String phoneNumber, String description);

    // get all data about user

    User getDataAboutUser(int id);

    List<User> getAllDataAboutUser();

    // check if user exist - email

    boolean checkIfUserExists(String email);

    // login check if user exist - email + password

    boolean checkIfValidLogin(String email, String password);

    // update

    void editRegularProfile(String firstName, String lastName, String birthOfDate, String email);

    void editWorkerProfile(String firstName, String lastName, String birthOfDate, String email, String description, String phoneNumber, List<String> profession);

}
