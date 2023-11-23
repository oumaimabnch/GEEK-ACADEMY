package com.gdev.geekacademybackend.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.gdev.geekacademybackend.models.Course;
import com.gdev.geekacademybackend.models.Session;
import com.gdev.geekacademybackend.models.WeeklyScheduledSession;
import com.gdev.geekacademybackend.repositories.CourseRepository;
import com.gdev.geekacademybackend.repositories.SessionRepository;
import com.gdev.geekacademybackend.repositories.WeeklyScheduledSessionRepository;
import com.gdev.geekacademybackend.services.dto.SessionDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private WeeklyScheduledSessionRepository weeklyScheduledSessionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CourseRepository courseRepository;

    public void addWeeklySessions(Course course, SessionDto sessionDto) {

        WeeklyScheduledSession weeklyScheduledSession1 = modelMapper.map(sessionDto, WeeklyScheduledSession.class);
        WeeklyScheduledSession weeklyScheduledSession = weeklyScheduledSessionRepository.save(weeklyScheduledSession1);

        course.addWeeklyScheduledSession(weeklyScheduledSession);
        listWeeklyDaysBetween(course.getStartingDate(), course.getEndingDate(), sessionDto.getDayOfWeek())
                .forEach(date -> {
                    Session session = modelMapper.map(sessionDto, Session.class);
                    session.setHappenAt(date);
                    session.setWeeklyScheduledSession(weeklyScheduledSession);
                    addSessionToSchedule(course, session, weeklyScheduledSession);
                });
    }

    public void addSingleSessions(Course course, Session session) {
        session = sessionRepository.save(session);
        course.addSession(session);
        courseRepository.save(course);
    }

    public void addSessionToSchedule(Course course, Session sessiond, WeeklyScheduledSession weeklyScheduledSession) {
        Session session = sessionRepository.save(sessiond);
        course.addSession(session);
        courseRepository.save(course);
    }

    public List<LocalDate> listWeeklyDaysBetween(LocalDate start, LocalDate end, DayOfWeek dayOfWeek) {
        return start.datesUntil(end)
                .filter(date -> date.getDayOfWeek().equals(dayOfWeek))
                .collect(Collectors.toList());
    }

}
