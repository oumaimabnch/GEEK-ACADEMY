package com.gdev.geekacademybackend.repositories;

import java.util.Optional;

import com.gdev.geekacademybackend.models.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    public Optional<File> findById(String id);
}
