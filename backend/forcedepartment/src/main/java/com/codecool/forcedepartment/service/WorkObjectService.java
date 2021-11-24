package com.codecool.forcedepartment.service;

import com.codecool.forcedepartment.dal.WorkObjectRepository;
import com.codecool.forcedepartment.model.WorkObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkObjectService {

    private WorkObjectRepository workObjectRepository;

    @Autowired
    public WorkObjectService(WorkObjectRepository workObjectRepository) {
        this.workObjectRepository = workObjectRepository;
    }

    public List<String> getAllWorkObjects() {
        return workObjectRepository.findAll()
                .stream().map(WorkObject::getWork_object)
                .collect(Collectors.toList());
    }
}
