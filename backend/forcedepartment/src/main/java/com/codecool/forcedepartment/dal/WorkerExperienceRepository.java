package com.codecool.forcedepartment.dal;

import com.codecool.forcedepartment.model.WorkerExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerExperienceRepository extends JpaRepository<WorkerExperience, Long> {
}
