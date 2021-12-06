package com.codecool.forcedepartment.dal;

import com.codecool.forcedepartment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT MAX(u.id) from User u")
    Long getLatestId();

    @Query(value = "SELECT u.id\n FROM User u WHERE u.email =:email")
    Long isEmailAlreadyInExist(@Param("email") String email);

    @Query(value = "SELECT u.id\n FROM User u WHERE u.email =:email AND u.password =:password")
    Long isUserInExist(@Param("email") String email, @Param("password") String password);

    User findByEmail(String email);
}
