package com.gdev.geekacademybackend.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@DiscriminatorValue(value = "Student")
@Data
public class Student extends User {

    @Column(nullable = false)
    private Long tutoPhone;

    @ManyToMany(mappedBy = "students")
    private Collection<Course> courses;

}
