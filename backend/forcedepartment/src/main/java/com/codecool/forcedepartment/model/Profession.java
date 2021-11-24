package com.codecool.forcedepartment.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profession {

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
