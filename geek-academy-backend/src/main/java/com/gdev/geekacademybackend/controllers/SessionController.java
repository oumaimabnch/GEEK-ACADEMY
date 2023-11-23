package com.gdev.geekacademybackend.controllers;

import com.gdev.geekacademybackend.exceptions.ResourceNotFoundException;
import com.gdev.geekacademybackend.models.Course;
import com.gdev.geekacademybackend.models.Session;
import com.gdev.geekacademybackend.repositories.CourseRepository;
import com.gdev.geekacademybackend.services.CourseService;
import com.gdev.geekacademybackend.services.SessionService;
import com.gdev.geekacademybackend.services.dto.SessionDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses/{id}/sessions")
public class SessionController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/weekly")
    public ResponseEntity<?> addSingleSession(@PathVariable(name = "id") Long id, @RequestBody SessionDto sessionDto) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        sessionService.addWeeklySessions(course, sessionDto);
        return new ResponseEntity<>("Weekly Sessions added successfully", HttpStatus.CREATED);

    }

    @PostMapping("/single")
    public ResponseEntity<?> addWeeklySession(@PathVariable(name = "id") Long id, @RequestBody SessionDto sessionDto) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        Session session = modelMapper.map(sessionDto, Session.class);
        sessionService.addSingleSessions(course, session);
        return new ResponseEntity<>("Single session added successfully", HttpStatus.CREATED);

    }

}
