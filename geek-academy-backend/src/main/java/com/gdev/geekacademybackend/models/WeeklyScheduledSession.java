package com.gdev.geekacademybackend.models;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class WeeklyScheduledSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Time startingTime;

    @Column(nullable = false)
    private Time endingTime;

    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    @ManyToOne
    private Course course;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "weeklyScheduledSession")
    private Collection<Session> sessions;

    public void addSession(Session session) {
        this.sessions.add(session);
    }

}