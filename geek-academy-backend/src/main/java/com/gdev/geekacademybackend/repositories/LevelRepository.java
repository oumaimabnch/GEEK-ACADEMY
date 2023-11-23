package com.gdev.geekacademybackend.repositories;

import com.gdev.geekacademybackend.models.Level;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {

}
