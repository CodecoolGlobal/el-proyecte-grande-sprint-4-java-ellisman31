package com.codecool.forcedepartment.service;

import com.codecool.forcedepartment.dal.UserRepository;
import com.codecool.forcedepartment.dal.WorkerRepository;
import com.codecool.forcedepartment.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    private WorkerRepository workerRepository;
    private UserRepository userRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository, UserRepository userRepository) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
    }


    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public List<Worker> getAllWorkersByRating() {
        return workerRepository.orderByRating();
    }

    public void addWorkerToDatabase(Worker worker) {
        workerRepository.save(worker);
        worker.addUserDataToWorker(userRepository.findById(worker.getUser_id()).get());
    }

    public Worker getWorkerById(Long id) {
        return workerRepository.findWorkerByUser_id(id);
    }

}
