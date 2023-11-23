package com.gdev.geekacademybackend.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@DiscriminatorValue(value = "Instructor")
public class Instructor extends User {

    @Column(nullable = false)
    private Long ncin;

    @NotBlank
    @Column(nullable = false)
    private String grade;

    @Column(nullable = true)
    @Lob
    private String profileOverview;

    @OneToMany
    @JoinColumn(name = "instructor")
    private Collection<Course> courses;

    @OneToMany
    @JoinColumn(name = "instructor_id")
    private Collection<Post> posts;

}
