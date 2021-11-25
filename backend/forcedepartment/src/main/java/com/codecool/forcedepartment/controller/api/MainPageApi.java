package com.codecool.forcedepartment.controller.api;

import com.codecool.forcedepartment.model.Worker;
import com.codecool.forcedepartment.model.WorkerExperience;
import com.codecool.forcedepartment.service.ProfessionService;
import com.codecool.forcedepartment.service.WorkObjectService;
import com.codecool.forcedepartment.service.WorkerExperienceService;
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
    private WorkerExperienceService workerExperienceService;

    @Autowired
    public MainPageApi(ProfessionService professionService,
                       WorkObjectService workObjectService,
                       WorkerService workerService,
                       WorkerExperienceService workerExperienceService) {
        this.professionService = professionService;
        this.workObjectService = workObjectService;
        this.workerService = workerService;
        this.workerExperienceService = workerExperienceService;
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
        return workerService.getAllWorkersByRating();
    }

    @RequestMapping(value = "/api/getWorkerById/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody  Worker getWorkerById(@PathVariable("userId") Long userId) {
        return workerService.getWorkerById(userId);
    }

    @RequestMapping(value = "/api/getAllWorkerExperience", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<WorkerExperience> getAllWorkerExperience() {
        return workerExperienceService.getAllWorkerExperience();
    }

}
