package com.bases.spboot.estudio.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "activities")
@ToString
@EqualsAndHashCode
public class Activity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private Long id;

    @Getter @Setter @Column(name = "title")
    private String title;

    @Getter @Setter @Column(name = "description")
    private String description;

    @Getter @Setter @Column(name = "userId")
    private Long userId;

    @Getter @Setter
    @OneToMany(targetEntity = ActivityTime.class ,cascade = CascadeType.ALL)
    @JoinColumn(name = "activityId", referencedColumnName = "id")
    private List<ActivityTime> activityTimes;
}
