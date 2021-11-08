package com.codecool.forcedepartment.controller.api;

import com.codecool.forcedepartment.dao.DatabaseManager;
import com.codecool.forcedepartment.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GetAllProfessionApi {

    DatabaseManager databaseManager;

    @Autowired
    public GetAllProfessionApi(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @CrossOrigin(origins = "http://localhost:3000")
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

}
