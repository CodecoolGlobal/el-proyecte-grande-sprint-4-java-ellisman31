package com.codecool.forcedepartment.controller.api;

import com.codecool.forcedepartment.dao.DatabaseManager;
import com.codecool.forcedepartment.model.util.GetLatestId;
import com.codecool.forcedepartment.model.User;
import com.codecool.forcedepartment.model.Worker;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class GetAllUsersApi {

    DatabaseManager databaseManager;
    private static int workerId;

    @Autowired
    public GetAllUsersApi(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @RequestMapping(value = "/api/getAllUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<User> getAllRegularUser() {
        return databaseManager.getAllDataAboutUser();
    }

    @RequestMapping(value = "/api/getAllUser", method = RequestMethod.POST)
    public void addRegularUser(@RequestBody String userJson) throws JSONException, ParseException {

        JSONObject user = new JSONObject(userJson);

        String firstName = user.getString("firstName");
        String lastName = user.getString("lastName");
        String birthOfDate = user.getString("birthOfDate");
        String userType = user.getString("userType");
        String password = user.getString("password");
        String email = user.getString("email");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthOfDateParsed = formatter.parse(birthOfDate);

        User newUser = new User(firstName, lastName, birthOfDateParsed, userType, email);

        workerId = databaseManager.registerRegularUser(newUser, password);

    }

    @RequestMapping(value = "/api/getAllWorker", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Worker> getAllWorker() {
        return databaseManager.getWorkersByRating();
    }

    @RequestMapping(value = "/api/getAllWorker", method = RequestMethod.POST)
    public void addWorker(@RequestBody String workerJson) throws JSONException {
        JSONObject worker = new JSONObject(workerJson);

        String description = worker.getString("description");
        String phoneNumber = worker.getString("telephoneNumber");
        int workerId = Integer.parseInt(worker.getString("latestId"));

        System.out.println(workerJson);
        databaseManager.registerWorker(workerId, phoneNumber, description);
    }

    @RequestMapping(value="/api/getLatestId", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<GetLatestId> getLatestId() {
        List<GetLatestId> getLatestId = new ArrayList<>();
        getLatestId.add(new GetLatestId(workerId));
        return getLatestId;
    }

}
