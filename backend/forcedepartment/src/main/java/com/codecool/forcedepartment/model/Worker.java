package com.codecool.forcedepartment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

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

    //TODO: connect with profession (multiple joinColumns)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "workerExperience",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    @JsonManagedReference
    private List<WorkerExperience> workerExperience;

    public void addUserDataToWorker(User user) {
        this.user = user;
    }

    public Worker(Long user_id, String phone_number, boolean is_available, double rate, String description) {
        this.user_id = user_id;
        this.phone_number = phone_number;
        this.is_available = is_available;
        this.rate = rate;
        this.description = description;
    }
}
