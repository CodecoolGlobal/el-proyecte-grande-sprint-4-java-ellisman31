package com.codecool.forcedepartment.service;

import com.codecool.forcedepartment.dal.UserRepository;
import com.codecool.forcedepartment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void addUserToDatabase(User user) {
        userRepository.save(user);
    }

    public void deleteUserFromDatabase(Long id) {
        userRepository.deleteById(id);
    }


}
