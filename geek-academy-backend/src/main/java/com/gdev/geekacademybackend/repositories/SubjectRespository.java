package com.gdev.geekacademybackend.repositories;

import java.util.Optional;

import com.gdev.geekacademybackend.models.Subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRespository extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);
}
