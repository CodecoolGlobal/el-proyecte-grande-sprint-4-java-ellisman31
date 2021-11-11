package com.codecool.forcedepartment.controller.api;

import com.codecool.forcedepartment.dao.DatabaseManager;
import com.codecool.forcedepartment.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class GetAllProfessionApi {

    DatabaseManager databaseManager;

    @Autowired
    public GetAllProfessionApi(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @RequestMapping(value = "/api/getAllProfession", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<String> getAllProfession() {
        return databaseManager.getAllProfession();
    }

    @RequestMapping(value = "/api/getAllWorkObject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<String> getAllWorkObject() {
        return databaseManager.getAllWorkObject();
    }

    @RequestMapping(value = "/api/getAllWorkerByProfession/{profession}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Worker> getAllWorkerByProfession(
            @PathVariable("profession") String profession) {
        return databaseManager.getWorkersByProfession(profession);
    }

    @RequestMapping(value = "/api/getAllWorkerByWorkObject/{workObject}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Worker> getAllWorkerByWorkObject(
            @PathVariable("workObject") String workObject) {
        return databaseManager.getWorkersByWorkObject(workObject);
    }

    @RequestMapping(value = "/api/getWorkersByRating", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Worker> getAllWorkersByRating() {
        return databaseManager.getWorkersByRating();
    }


    @RequestMapping(value = "/api/getUserById/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody User getWorkerById(
            @PathVariable("userId") int userId
    ) { return databaseManager.getDataAboutUser(userId);}


}
