package com.codecool.forcedepartment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"id", "workRequirement"})
public class Profession {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String profession_name;

    @OneToMany(mappedBy = "profession", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<WorkerExperience> workerExperience;

    @ManyToMany()
    @JoinTable(
            name = "workRequirement",
            joinColumns = @JoinColumn(name = "profession_id", foreignKey = @ForeignKey(name = "fk_profession_id",
                    value = ConstraintMode.NO_CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    @JsonManagedReference
    private List<WorkRequirement> workRequirement;

}
