package com.codecool.forcedepartment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "website_user", schema = "public")
@JsonIgnoreProperties(value = {"id"})
public class User {

    @Id
    @SequenceGenerator(
            name = "website_user_id_seq",
            sequenceName = "website_user_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String first_name;
    private String last_name;
    private Date birth_date;
    private String email;
    private boolean is_admin;
    private String password;
    private Date registration_date;
    private String group_name;

    //private String image = "profile-icon-empty.png";
    //private String imageName;
    //private static final boolean IS_ADMIN = false;
    //private String profileImage;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Worker> worker;
}
