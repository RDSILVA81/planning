package com.teamway.planning.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Worker {

    @Id
    @SequenceGenerator(
            name="worker_sequence",
            sequenceName = "worker_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "worker_sequence"
    )
    private Integer id;
    @Column(unique = true)
    private Integer badgeNumber;
    private String firstName;
    private String lastName;
    private Date dateOfBirthday;

    @OneToMany
    @JoinColumn(name="worker_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Shift> shift;

}
