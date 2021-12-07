package com.codecool.forcedepartment.service;

import com.codecool.forcedepartment.dal.UserRepository;
import com.codecool.forcedepartment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Email not found in the database!");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.get().getGroup_name()));
        return new org.springframework.security.core.userdetails.User(user.get().getEmail(),
                passwordEncoder.encode(user.get().getPassword()), authorities);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void addUserToDatabase(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteUserFromDatabase(Long id) {
        userRepository.deleteById(id);
    }

    public Long getTheLatestId() {
        return userRepository.getLatestId();
    }

    public boolean isEmailAlreadyInExist(String email) {
        return userRepository.isEmailAlreadyInExist(email) != null;
    }

    public boolean isUserInExist(String email, String password) {
        return userRepository.isUserInExist(email, password) != null;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
