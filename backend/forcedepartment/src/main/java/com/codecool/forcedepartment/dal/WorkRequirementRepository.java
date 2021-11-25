package com.codecool.forcedepartment.dal;

import com.codecool.forcedepartment.model.WorkRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRequirementRepository extends JpaRepository<WorkRequirement, Long> {
}
