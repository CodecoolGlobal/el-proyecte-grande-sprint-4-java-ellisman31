package com.codecool.forcedepartment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"id"})
public class Profession {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String profession_name;

    @OneToMany(mappedBy = "profession", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<WorkerExperience> workerExperience;
}
