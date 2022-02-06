package com.teamway.planning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamway.planning.util.TimeTable;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Shift {
    @Id
    @SequenceGenerator(
            name="shift_sequence",
            sequenceName = "shift_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "shift_sequence"
    )
    private Integer id;
    private Date date;
    @Enumerated(EnumType.STRING)
    private TimeTable timeTable;
    @ManyToOne
    @JoinColumn(name="worker_id")
    @JsonIgnore
    private Worker worker;
}
