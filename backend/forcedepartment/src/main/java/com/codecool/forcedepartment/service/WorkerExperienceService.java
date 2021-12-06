package com.codecool.forcedepartment.service;

import com.codecool.forcedepartment.dal.WorkerExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.codecool.forcedepartment.model.WorkerExperience;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerExperienceService {

    WorkerExperienceRepository workerExperienceRepository;

    @Autowired
    public WorkerExperienceService(WorkerExperienceRepository workerExperienceRepository) {
        this.workerExperienceRepository = workerExperienceRepository;
    }

    public List<WorkerExperience> getAllWorkerExperience() {
        return workerExperienceRepository.findAll();
    }
}
