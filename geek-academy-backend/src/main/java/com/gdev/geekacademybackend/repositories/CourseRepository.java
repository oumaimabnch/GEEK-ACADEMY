package com.gdev.geekacademybackend.repositories;

import com.gdev.geekacademybackend.models.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}