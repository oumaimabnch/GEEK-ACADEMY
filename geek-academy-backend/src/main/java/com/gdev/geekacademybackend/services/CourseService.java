package com.gdev.geekacademybackend.services;

import java.util.List;

import javax.transaction.Transactional;

import com.gdev.geekacademybackend.exceptions.ResourceNotFoundException;
import com.gdev.geekacademybackend.models.Course;
import com.gdev.geekacademybackend.models.Student;
import com.gdev.geekacademybackend.repositories.CourseRepository;
import com.gdev.geekacademybackend.repositories.SessionRepository;
import com.gdev.geekacademybackend.repositories.SubjectRespository;
import com.gdev.geekacademybackend.security.services.UserDetailsImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @Autowired
    SubjectRespository subjectRespository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Transactional
    public Course createCourse(Course Course) {
        return courseRepository.save(Course);
    }

    public Course updateCourse(long id, Course CourseRequest) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        return courseRepository.save(course);
    }

    public void deleteCourse(long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        courseRepository.delete(course);
    }

    public Course getCourseById(long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        return course;

        
    }

    public void subscribeOnCourse(Long courseId) {
        UserDetailsImpl uDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Student student = userService.getStudent(uDetailsImpl.getUsername());

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", courseId));
        course.addStudent(student);
        courseRepository.save(course);

    }

}
