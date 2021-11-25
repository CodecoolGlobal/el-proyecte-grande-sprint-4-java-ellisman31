package com.codecool.forcedepartment.controller.api;

import com.codecool.forcedepartment.model.Worker;
import com.codecool.forcedepartment.model.WorkerExperience;
import com.codecool.forcedepartment.model.WorkRequirement;
import com.codecool.forcedepartment.service.*;
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
    private WorkRequirementService workRequirementService;

    @Autowired
    public MainPageApi(ProfessionService professionService,
                       WorkObjectService workObjectService,
                       WorkerService workerService,
                       WorkerExperienceService workerExperienceService,
                       WorkRequirementService workRequirementService) {
        this.professionService = professionService;
        this.workObjectService = workObjectService;
        this.workerService = workerService;
        this.workerExperienceService = workerExperienceService;
        this.workRequirementService = workRequirementService;
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

    @RequestMapping(value = "/api/getAllWorkerExperience", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<WorkerExperience> getAllWorkerExperience() {
        return workerExperienceService.getAllWorkerExperience();
    }

    @RequestMapping(value = "/api/getAllWorkerRequirement", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<WorkRequirement> getAllWorkerRequirement() {
        return workRequirementService.getAllWorkRequirement();
    }

}
