package com.gdev.geekacademybackend.repositories;

import com.gdev.geekacademybackend.models.Instructor;

import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends UserBaseRepository<Instructor> {
}
