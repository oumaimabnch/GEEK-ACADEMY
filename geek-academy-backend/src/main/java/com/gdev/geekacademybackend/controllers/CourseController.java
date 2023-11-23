package com.gdev.geekacademybackend.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import com.gdev.geekacademybackend.exceptions.ResourceNotFoundException;
import com.gdev.geekacademybackend.models.Course;
import com.gdev.geekacademybackend.models.Subject;
import com.gdev.geekacademybackend.repositories.CourseRepository;
import com.gdev.geekacademybackend.repositories.SubjectRespository;
import com.gdev.geekacademybackend.services.CourseService;
import com.gdev.geekacademybackend.services.SubjectService;
import com.gdev.geekacademybackend.services.UserService;
import com.gdev.geekacademybackend.services.dto.CourseDto;
import com.gdev.geekacademybackend.services.dto.SessionDto;
import com.gdev.geekacademybackend.services.payload.ApiResponse;
import com.gdev.geekacademybackend.services.payload.UserSummary;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    SubjectRespository subjectRespository;

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<CourseDto> getAllCourses() {

        return courseService.getAllCourses().stream().map(course -> {
            CourseDto courseDto = modelMapper.map(course, CourseDto.class);
            UserSummary userSummary = userService.getUserSummary(course.getInstructor());
            courseDto.setInstructor(userSummary);
            return courseDto;
        })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable(name = "id") Long id) {
        Course course = courseService.getCourseById(id);

        // convert entity to DTO
        CourseDto courseResponse = modelMapper.map(course, CourseDto.class);
        UserSummary userSummary = userService.getUserSummary(course.getInstructor());
        courseResponse.setInstructor(userSummary);

        return ResponseEntity.ok().body(courseResponse);
    }

    @PostMapping
    @RolesAllowed({ "INSTRUCTOR", "ADMIN", "PLANNER" })
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto) {

        // convert DTO to entity
        Course courseRequest = modelMapper.map(courseDto, Course.class);
        Subject subject = subjectService.getSubjectByName(courseDto.getSubjectName());
        courseRequest.setSubject(subject);
        Course course = courseService.createCourse(courseRequest);

        // convert entity to DTO
        CourseDto courseResponse = modelMapper.map(course, CourseDto.class);

        return new ResponseEntity<CourseDto>(courseResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable long id, @RequestBody CourseDto courseDto) {

        // convert DTO to Entity
        Course courseRequest = modelMapper.map(courseDto, Course.class);

        Course course = courseService.updateCourse(id, courseRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{courseId}")
                .buildAndExpand(course.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Course Created Successfully", HttpStatus.CREATED));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable(name = "id") Long id) {
        courseService.deleteCourse(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Course deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/{id}/subscribe")
    @RolesAllowed({ "STUDENT" })
    public ResponseEntity<ApiResponse> subscribeOnCourse(@PathVariable(name = "id") Long id) {
        courseService.subscribeOnCourse(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "student subscribed successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

}
