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
@JsonIgnoreProperties(value = {"id", "work_object_id", "profession_id"})
@Table(name = "work_requirement", schema = "public")
public class WorkRequirement {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long work_object_id;
    private Long profession_id;

    @ManyToMany(mappedBy = "workRequirement")
    @JsonBackReference
    private Set<Profession> profession;

    @ManyToMany(mappedBy = "workRequirement")
    @JsonBackReference
    private Set<WorkObject> workObject;
}
