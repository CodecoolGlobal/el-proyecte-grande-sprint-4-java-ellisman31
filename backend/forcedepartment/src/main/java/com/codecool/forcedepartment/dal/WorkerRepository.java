package com.codecool.forcedepartment.dal;

import com.codecool.forcedepartment.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @Query(value = "SELECT w from Worker w order by w.rate desc")
    List<Worker> orderByRating();

    @Query(value = "SELECT DISTINCT w FROM Worker w " +
            "JOIN w.user u JOIN u.workerExperience e " +
            "JOIN e.profession p WHERE LOWER(p.profession_name) " +
            "LIKE LOWER(CONCAT('%',:profession,'%'))")
    List<Worker> getAllWorkersByProfession(@Param("profession") String profession);

    @Query(value = "SELECT DISTINCT w FROM Worker w " +
            "JOIN w.user u JOIN u.workerExperience e JOIN e.profession p JOIN p.workRequirement r JOIN r.workObject o " +
            "WHERE LOWER(o.work_object) LIKE LOWER(CONCAT('%',:workObject,'%'))")
    List<Worker> getAllWorkersByWorkObject(@Param("workObject") String workObject);

    @Query(value = "SELECT DISTINCT w FROM Worker w " +
            "JOIN w.user u JOIN u.workerExperience e JOIN e.profession p JOIN p.workRequirement r JOIN r.workObject o " +
            "WHERE LOWER(o.work_object) LIKE LOWER(CONCAT('%',:workObject,'%')) AND " +
            "LOWER(p.profession_name) LIKE LOWER(CONCAT('%',:profession,'%')) AND " +
            "(LOWER(u.first_name) LIKE LOWER(CONCAT('%',:name,'%')) OR " +
            "LOWER(u.last_name) LIKE LOWER(CONCAT('%',:name,'%'))) AND " +
            "w.rate >= :rate")
    List<Worker> getAllWorkersByExtraFilter(@Param("name") String name,
                                @Param("workObject") String workObject,
                                @Param("profession") String profession,
                                @Param("rate") double rate);
}
