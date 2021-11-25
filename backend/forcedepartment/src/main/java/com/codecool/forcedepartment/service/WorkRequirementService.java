package com.codecool.forcedepartment.service;

import com.codecool.forcedepartment.dal.WorkRequirementRepository;
import com.codecool.forcedepartment.model.WorkRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkRequirementService {

    private WorkRequirementRepository workRequirementRepository;

    @Autowired
    public WorkRequirementService(WorkRequirementRepository workerRequirementRepository) {
        this.workRequirementRepository = workerRequirementRepository;
    }


    public List<WorkRequirement> getAllWorkRequirement() {
        return workRequirementRepository.findAll();
    }
}
