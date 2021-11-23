package com.codecool.forcedepartment.service;

import com.codecool.forcedepartment.dal.WorkerRepository;
import com.codecool.forcedepartment.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    private WorkerRepository workerRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> getAllWorkersByRate() {
        return workerRepository.findAll();
    }
}
