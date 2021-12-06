package com.codecool.forcedepartment.controller.api;

import com.codecool.forcedepartment.model.User;
import com.codecool.forcedepartment.model.Worker;
import com.codecool.forcedepartment.service.UserService;
import com.codecool.forcedepartment.service.WorkerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class GetAllUsersApi {

    private UserService userService;
    private WorkerService workerService;

    @Autowired
    public GetAllUsersApi(UserService userService, WorkerService workerService) {
        this.userService = userService;
        this.workerService = workerService;
    }

    @RequestMapping(value = "/api/getAllUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<User> getAllUser() {
        return userService.getAllUser();
    }

    @ResponseBody @RequestMapping(value = "/api/getAllUser", method = RequestMethod.POST)
    public String addUser(@RequestBody String userJson) throws JSONException, ParseException {
        JSONObject user = new JSONObject(userJson);

        String firstName = user.getString("firstName");
        String lastName = user.getString("lastName");
        String birthOfDate = user.getString("birthOfDate");
        String userType = user.getString("userType");
        String password = user.getString("password");
        String email = user.getString("email");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthOfDateParsed = formatter.parse(birthOfDate);

        User newUser = new User(firstName, lastName, birthOfDateParsed, email, password, userType);
        userService.addUserToDatabase(newUser);
        return "User created";
    }

    @RequestMapping(value = "/api/getAllWorker", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Worker> getAllWorker() {
        return workerService.getAllWorkers();
    }

    @ResponseBody @RequestMapping(value = "/api/getAllWorker", method = RequestMethod.POST)
    public String addWorker(@RequestBody String workerJson) throws JSONException {
        JSONObject workerData = new JSONObject(workerJson);

        String description = workerData.getString("description");
        String phoneNumber = workerData.getString("telephoneNumber");

        Worker worker = new Worker(userService.getTheLatestId(), phoneNumber, false, 0, description);

        workerService.addWorkerToDatabase(worker);
        return "Worker created";
    }

}
