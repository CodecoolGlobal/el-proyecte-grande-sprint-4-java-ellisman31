package com.codecool.forcedepartment.dal;

import com.codecool.forcedepartment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
