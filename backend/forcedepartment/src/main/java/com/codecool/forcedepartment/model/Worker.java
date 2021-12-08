package com.codecool.forcedepartment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;
    private String phone_number;
    private double rate;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonManagedReference
    private User user;

    public void addUserDataToWorker(User user) {
        this.user = user;
    }

    public Worker(Long user_id, String phone_number, double rate, String description) {
        this.user_id = user_id;
        this.phone_number = phone_number;
        this.rate = rate;
        this.description = description;
    }
}
