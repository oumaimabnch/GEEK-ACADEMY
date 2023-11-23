package com.gdev.geekacademybackend.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.gdev.geekacademybackend.models.Auditing.DateAuditor;

import org.springframework.data.annotation.CreatedBy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course extends DateAuditor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private LocalDate startingDate;

    @Column(nullable = false)
    private LocalDate endingDate;

    @Column(nullable = true)
    private Integer capacity = 20;

    @OneToOne
    @JoinColumn(nullable = true)
    private File cover;

    @Column(nullable = true)
    private String contentIndex;

    @Column
    private Boolean approved = false;

    @CreatedBy
    private Long instructor;

    @ManyToOne
    private Subject subject;

    @ManyToMany
    @JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    private Collection<Student> students;

    @ManyToMany
    @JoinTable(name = "course_level", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "level_id", referencedColumnName = "id"))
    private Collection<Level> levels;

    @OneToMany
    @JoinColumn(name = "course_id")
    private Collection<Session> sessions;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Collection<Post> posts;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<WeeklyScheduledSession> weeklyScheduledSessions;

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    public void addWeeklyScheduledSession(WeeklyScheduledSession weeklyScheduledSession) {
        this.weeklyScheduledSessions.add(weeklyScheduledSession);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

}
