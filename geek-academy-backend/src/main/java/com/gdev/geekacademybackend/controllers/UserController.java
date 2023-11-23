package com.gdev.geekacademybackend.controllers;

import static org.springframework.http.ResponseEntity.status;

import java.util.Collection;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import com.gdev.geekacademybackend.models.Instructor;
import com.gdev.geekacademybackend.models.RoleName;
import com.gdev.geekacademybackend.models.Staff;
import com.gdev.geekacademybackend.models.Student;
import com.gdev.geekacademybackend.models.User;
import com.gdev.geekacademybackend.services.UserService;
import com.gdev.geekacademybackend.services.dto.InstructorDto;
import com.gdev.geekacademybackend.services.dto.StaffDto;
import com.gdev.geekacademybackend.services.dto.StudentDto;
import com.gdev.geekacademybackend.services.payload.LoginRequest;
import com.gdev.geekacademybackend.services.payload.PasswordChangeDTO;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("login")
    private ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        System.out.println("-----------------------------------------------");
        logger.info(loginRequest.getUsername());
        System.out.println("-----------------------------------------------");
        return userService.authenticateUser(loginRequest);
    }

    @PostMapping("/instructor")
    @ResponseBody
    private ResponseEntity<?> register(@Valid @RequestBody InstructorDto instructorRequest) {

        // convert the coming registration request to entity
        Instructor instructorDto = modelMapper.map(instructorRequest, Instructor.class);
        Instructor instructor = (Instructor) userService.createUser(instructorDto);
        // convert instructor entity to instructor DTO
        InstructorDto instructorResponse = modelMapper.map(instructor, InstructorDto.class);

        return new ResponseEntity<InstructorDto>(instructorResponse, HttpStatus.CREATED);

    }

    @PostMapping("/student")
    @ResponseBody
    private ResponseEntity<?> register(@Valid @RequestBody StudentDto studentRequest) {

        // convert the coming registration request to entity
        Student studentDto = modelMapper.map(studentRequest, Student.class);
        Student student = (Student) userService.createUser(studentDto);
        // convert instructor entity to instructor DTO
        StudentDto studentResponse = modelMapper.map(student, StudentDto.class);

        return new ResponseEntity<StudentDto>(studentResponse, HttpStatus.CREATED);

    }

    @PostMapping("/staff")
    @ResponseBody
    private ResponseEntity<?> register(@Valid @RequestBody StaffDto staffRequest) {

        // convert the coming registration request to entity
        Staff staffDto = modelMapper.map(staffRequest, Staff.class);
        Staff staff = (Staff) userService.createUser(staffDto);
        // convert instructor entity to instructor DTO
        StaffDto staffResponse = modelMapper.map(staff, StaffDto.class);

        return new ResponseEntity<StaffDto>(staffResponse, HttpStatus.CREATED);

    }

    @PatchMapping("/student/{username}/approve")
    @RolesAllowed({ "ADMIN", "INSTRUCTORS_MANAGER" })
    @ResponseBody
    private ResponseEntity<?> approveStudent(@PathVariable String username) {

        userService.approvedStudent(username);

        return new ResponseEntity<>("Student Approved ", HttpStatus.OK);

    }

    @PatchMapping("/instructor/{username}/approve")
    @RolesAllowed({ "ADMIN", "INSTRUCTORS_MANAGER" })
    @ResponseBody
    private ResponseEntity<?> approveInstructor(@PathVariable String username) {

        userService.approveInstructor(username);

        return new ResponseEntity<>("Instructor Approved ", HttpStatus.OK);
    }

    @PostMapping("/users/{username}/roles")
    @RolesAllowed({ "ADMIN" })
    @ResponseBody
    private ResponseEntity<?> addRolesToUser(@PathVariable String username, @RequestBody List<RoleName> roles) {
        userService.addListRolesToUser(roles, username);

        return new ResponseEntity<>("Roles added ", HttpStatus.OK);
    }

    @GetMapping("/users")
    public Collection<User> getAllUsers() {
        return userService.retrieveAllUsers();

    }
}
