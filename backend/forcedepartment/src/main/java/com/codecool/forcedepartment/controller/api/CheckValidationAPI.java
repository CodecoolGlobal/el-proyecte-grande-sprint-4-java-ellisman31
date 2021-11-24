package com.codecool.forcedepartment.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class CheckValidationAPI {

    //TODO: finish the APIs
    //@Autowired

    @RequestMapping(value = "/api/ifEmailExist/{email}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody boolean checkEmailInUse(@PathVariable("email") String email){
        return false; //databaseManager.checkIfEmailInUse(email);
    }

    @RequestMapping(value = "/api/checkUserIsExist/{email}:{password}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody boolean checkUserIsExist(@PathVariable("email") String email,
                                                  @PathVariable("password") String password) {

        return false;//databaseManager.checkValidLogin(email, password);
    }
}
