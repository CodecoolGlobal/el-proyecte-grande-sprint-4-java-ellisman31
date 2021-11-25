package com.codecool.forcedepartment.controller.api;

import com.codecool.forcedepartment.model.Worker;
import com.codecool.forcedepartment.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class SidebarMenuPointApi {


    private WorkerService workerService;

    @Autowired
    public SidebarMenuPointApi(WorkerService workerService) {
        this.workerService = workerService;
    }

    @RequestMapping(value = "/api/getAllWorkerByProfession/{profession}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Worker> getAllWorkerByProfession(
            @PathVariable("profession") String profession) {
        return workerService.getAllWorkersByProfession(profession);
    }


    @RequestMapping(value = "/api/getAllWorkerByWorkObject/{workObject}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Worker> getAllWorkerByWorkObject(
            @PathVariable("workObject") String workObject) {
        return workerService.getAllWorkersByWorkObject(workObject);
    }


    @RequestMapping(value = "/api/getWorkerByExtraSearch/{name}/{workObject}/{profession}/{rating}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Worker> getWorkersByExtraFilter(
            @PathVariable("name") String name,
            @PathVariable("workObject") String workObject,
            @PathVariable("profession") String profession,
            @PathVariable("rating") double rating
    ) {
        return workerService.getAllWorkersByExtraFilter(name, workObject, profession, rating);
    }

}
