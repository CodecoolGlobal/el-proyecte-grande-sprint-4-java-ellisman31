package com.codecool.forcedepartment.dal;

import com.codecool.forcedepartment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT MAX(u.id) from User u")
    Long getLatestId();
}
