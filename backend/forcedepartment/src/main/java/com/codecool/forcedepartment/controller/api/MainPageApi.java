package com.codecool.forcedepartment.controller.api;

import com.codecool.forcedepartment.dao.DatabaseManager;
import com.codecool.forcedepartment.model.Profession;
import com.codecool.forcedepartment.model.Worker;
import com.codecool.forcedepartment.service.ProfessionService;
import com.codecool.forcedepartment.service.WorkObjectService;
import com.codecool.forcedepartment.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class MainPageApi {

    private ProfessionService professionService;
    private WorkObjectService workObjectService;
    private WorkerService workerService;

    @Autowired
    public MainPageApi(ProfessionService professionService,
                       WorkObjectService workObjectService,
                       WorkerService workerService) {
        this.professionService = professionService;
        this.workObjectService = workObjectService;
        this.workerService = workerService;
    }


    @RequestMapping(value = "/api/getAllProfession", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<String> getAllProfession() {
        return professionService.getAllProfession();
    }

    @RequestMapping(value = "/api/getAllWorkObject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<String> getAllWorkObject() {
        return workObjectService.getAllWorkObjects();
    }

    @RequestMapping(value = "/api/getWorkersByRating", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Worker> getAllWorkersByRating() {
        return workerService.getAllWorkersByRate();
    }

}
