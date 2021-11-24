package com.codecool.forcedepartment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkerExperience {

    @Id
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long worker_id;

    private Long profession_id;
    private double experience_years;

    @ManyToMany(mappedBy = "workerExperience",  cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Worker> workers;

}
