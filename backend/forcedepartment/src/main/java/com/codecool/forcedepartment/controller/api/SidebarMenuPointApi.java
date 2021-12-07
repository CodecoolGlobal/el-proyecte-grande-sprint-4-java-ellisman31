package com.codecool.forcedepartment.controller.api;

import com.codecool.forcedepartment.model.Worker;
import com.codecool.forcedepartment.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SidebarMenuPointApi {

    private WorkerService workerService;

    @Autowired
    public SidebarMenuPointApi(WorkerService workerService) {
        this.workerService = workerService;
    }

    @RequestMapping(value = "/api/getAllWorkerByProfession/{profession}", method = RequestMethod.GET)
    public List<Worker> getAllWorkerByProfession(
            @PathVariable("profession") String profession) {
        return workerService.getAllWorkersByProfession(profession);
    }


    @RequestMapping(value = "/api/getAllWorkerByWorkObject/{workObject}", method = RequestMethod.GET)
    public List<Worker> getAllWorkerByWorkObject(
            @PathVariable("workObject") String workObject) {
        return workerService.getAllWorkersByWorkObject(workObject);
    }


    @RequestMapping(value = "/api/getWorkerByExtraSearch/{name}{workObject}{profession}{rating}",
            method = RequestMethod.GET)
    public List<Worker> getWorkersByExtraFilter(
            @RequestParam("name") String requestName,
            @RequestParam("workObject") String requestWorkObject,
            @RequestParam("profession") String requestProfession,
            @RequestParam("rating") String requestRating
    ) {

        double newRequestRating = 0;

        if (!Objects.equals(requestRating, "")) {
            newRequestRating = Double.parseDouble(requestRating);
        }

        return workerService.getAllWorkersByExtraFilter(requestName, requestWorkObject, requestProfession, newRequestRating);
    }

}
