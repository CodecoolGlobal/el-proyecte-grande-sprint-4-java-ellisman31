package com.codecool.forcedepartment.model;

import com.codecool.forcedepartment.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Worker {

    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "worker_id_seq",
            sequenceName = "worker_id_seq",
            allocationSize = 1
    )
    private Long id;

    private boolean is_available;
    private String description;
    private String phone_number;
    private double rate;

    //change into hashMap
    @ManyToMany
    private List<Profession> profession;


    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private int user_id;

    @ManyToOne
    private User user;

}
