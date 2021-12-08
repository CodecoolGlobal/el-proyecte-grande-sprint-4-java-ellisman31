package com.codecool.forcedepartment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String first_name;
    private String last_name;
    private Date birth_date;
    private String email;
    private String password;
    private LocalDateTime registration_date;
    private String role;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Worker> worker;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "workerExperience",
            joinColumns = @JoinColumn(name = "worker_id", foreignKey = @ForeignKey(name = "fk_worker_id", value = ConstraintMode.NO_CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    @JsonManagedReference
    private List<WorkerExperience> workerExperience;

    public User(String first_name, String last_name, Date birth_date, String email, String password, String role_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.email = email;
        this.password = password;
        this.registration_date = registrationDate();
        this.role = role_name;
    }

    public LocalDateTime registrationDate() {
        return java.time.LocalDateTime.now();
    }
}
