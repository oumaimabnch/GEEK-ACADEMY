package com.gdev.geekacademybackend.repositories;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import com.gdev.geekacademybackend.models.Session;
import com.gdev.geekacademybackend.services.dto.SessionDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByStartingTimeAndCourseAndHappenAtIn(Time time, Long id, List<LocalDate> localDates);

    @Modifying
    @Query(value = "update Session s set s.StartingTime = time", nativeQuery = true)
    int updateSessions(Time time, Long id, List<LocalDate> localDates, SessionDto sessionDto);

}
