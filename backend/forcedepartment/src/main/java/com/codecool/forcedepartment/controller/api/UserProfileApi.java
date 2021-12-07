package com.codecool.forcedepartment.controller.api;

import com.codecool.forcedepartment.model.User;
import com.codecool.forcedepartment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserProfileApi {

    private UserService userService;

    @Autowired
    public UserProfileApi(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/getUserById/{userId}", method = RequestMethod.GET)
    public User getUserById(
            @PathVariable("userId") Long userId
    ) {
        return userService.getUserById(userId);
    }
}
