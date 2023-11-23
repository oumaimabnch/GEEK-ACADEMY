package com.gdev.geekacademybackend.repositories;

import com.gdev.geekacademybackend.models.WeeklyScheduledSession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyScheduledSessionRepository extends JpaRepository<WeeklyScheduledSession, Long> {

}
