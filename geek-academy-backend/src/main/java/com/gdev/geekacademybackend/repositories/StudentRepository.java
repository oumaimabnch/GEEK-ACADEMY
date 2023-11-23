package com.gdev.geekacademybackend.repositories;

import com.gdev.geekacademybackend.models.Student;

import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends UserBaseRepository<Student> {
    
}
