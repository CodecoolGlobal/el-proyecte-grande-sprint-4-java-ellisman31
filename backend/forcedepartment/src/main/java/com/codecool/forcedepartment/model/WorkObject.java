package com.codecool.forcedepartment.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WorkObject {

    @Id
    @SequenceGenerator(
            name = "work_object_id_seq",
            sequenceName = "work_object_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String work_object;
}
