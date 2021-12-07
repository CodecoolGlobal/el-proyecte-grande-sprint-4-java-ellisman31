package com.codecool.forcedepartment.controller.api;

import com.codecool.forcedepartment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CheckValidationAPI {

    private UserService userService;

    @Autowired
    public CheckValidationAPI(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/ifEmailExist/{email}", method = RequestMethod.GET)
    public boolean checkEmailInUse(@PathVariable("email") String email){
        return userService.isEmailAlreadyInExist(email);
    }

    @RequestMapping(value = "/api/checkUserIsExist/{email}:{password}", method = RequestMethod.GET)
    public boolean checkUserIsExist(@PathVariable("email") String email,
                                                  @PathVariable("password") String password) {
        return userService.isUserInExist(email, password);
    }
}
