package com.codecool.forcedepartment.dal;

import com.codecool.forcedepartment.model.WorkObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkObjectRepository extends JpaRepository<WorkObject, Long> {
}
