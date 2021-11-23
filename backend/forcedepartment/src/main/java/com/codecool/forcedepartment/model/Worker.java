package com.codecool.forcedepartment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "worker", schema = "public")
@JsonIgnoreProperties(value = {"id", "user_id"})
public class Worker {

    @Id
    @SequenceGenerator(
            name = "worker_id_seq",
            sequenceName = "worker_id_seq",
            allocationSize = 1
    )
    private Long id;

    private Long user_id;
    private String phone_number;
    private boolean is_available;
    private double rate;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName="id", insertable = false, updatable = false)
    @JsonManagedReference
    private User user;

//    @ManyToMany
//    @JoinTable(
//            name = "professions",
//            joinColumns = @JoinColumn(name = "worker_id"),
//            inverseJoinColumns = @JoinColumn(name = "profession_id")
//    )
//    private List<WorkerExperience> professions;

//    public void addUserDataToWorker(User user) {
//        this.user = user;
//    }

}
