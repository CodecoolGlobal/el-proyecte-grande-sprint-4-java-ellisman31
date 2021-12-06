package com.codecool.forcedepartment.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"id", "workRequirement"})
public class WorkObject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String work_object;

    @ManyToMany()
    @JoinTable(
            name = "workRequirement",
            joinColumns = @JoinColumn(name = "work_object_id", foreignKey = @ForeignKey(name = "fk_work_object_id",
                    value = ConstraintMode.NO_CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    @JsonManagedReference
    private List<WorkRequirement> workRequirement;
}
