package com.codecool.forcedepartment.dal;

import com.codecool.forcedepartment.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @Query(value = "SELECT w from Worker w order by w.rate desc")
    List<Worker> orderByRating();

    Worker findWorkerByUser_id(Long id);

}
