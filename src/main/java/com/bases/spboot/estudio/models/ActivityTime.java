package com.bases.spboot.estudio.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "activity_times")
@ToString
@EqualsAndHashCode
public class ActivityTime {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;

    @Getter @Setter @Column(name = "date")
    private String date;

    @Getter @Setter @Column(name = "totalHour")
    private int totalHour;

    @Getter @Setter @Column(name = "activityId")
    private Long activityId;
}
