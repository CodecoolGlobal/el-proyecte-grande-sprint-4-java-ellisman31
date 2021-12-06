package com.codecool.forcedepartment.controller.api;

import com.codecool.forcedepartment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class CheckValidationAPI {

    private UserService userService;

    @Autowired
    public CheckValidationAPI(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/ifEmailExist/{email}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody boolean checkEmailInUse(@PathVariable("email") String email){
        return userService.isEmailAlreadyInExist(email);
    }

    @RequestMapping(value = "/api/checkUserIsExist/{email}:{password}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody boolean checkUserIsExist(@PathVariable("email") String email,
                                                  @PathVariable("password") String password) {
        return userService.isUserInExist(email, password);
    }
}
