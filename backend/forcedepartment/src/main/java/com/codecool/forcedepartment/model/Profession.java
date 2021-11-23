package com.codecool.forcedepartment.model;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profession {

    //private Map<String, Double> professions = new HashMap<>();
    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "profession_id_seq",
            sequenceName = "profession_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String profession_name;

}
