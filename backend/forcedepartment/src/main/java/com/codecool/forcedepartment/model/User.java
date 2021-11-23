package com.codecool.forcedepartment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private Date registration_date;
    private Date birth_date;
    private boolean is_admin;
    private String password;
    private String email;
    private String group_name;
    private String image = "profile-icon-empty.png";
    //private String imageName;
    //private static final boolean IS_ADMIN = false;
    //private String profileImage;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Worker> workerSet;
}
