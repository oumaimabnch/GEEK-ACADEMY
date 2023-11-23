package com.gdev.geekacademybackend.models;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate happenAt;

    @Column(nullable = false)
    private Time startingTime;

    @Column(nullable = false)
    private Time endingTime;

    @ManyToOne
    @JoinColumn(name = "weeklyScheduledSession_id")
    private WeeklyScheduledSession weeklyScheduledSession;

    @ManyToOne
    private Course course;
}
