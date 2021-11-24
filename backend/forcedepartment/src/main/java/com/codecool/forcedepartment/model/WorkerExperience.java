package com.codecool.forcedepartment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"id","worker_id","profession_id"})
public class WorkerExperience {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long worker_id;
    private Long profession_id;
    private double experience_years;

    @ManyToMany(mappedBy = "workerExperience",  cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<User> user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "profession_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonManagedReference
    private Profession profession;

}
