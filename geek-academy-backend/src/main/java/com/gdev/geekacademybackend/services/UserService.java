package com.gdev.geekacademybackend.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.gdev.geekacademybackend.exceptions.AppException;
import com.gdev.geekacademybackend.exceptions.ResourceNotFoundException;
import com.gdev.geekacademybackend.models.Instructor;
import com.gdev.geekacademybackend.models.Role;
import com.gdev.geekacademybackend.models.RoleName;
import com.gdev.geekacademybackend.models.Staff;
import com.gdev.geekacademybackend.models.Student;
import com.gdev.geekacademybackend.models.User;
import com.gdev.geekacademybackend.repositories.InstructorRepository;
import com.gdev.geekacademybackend.repositories.RoleRepository;
import com.gdev.geekacademybackend.repositories.StaffRepository;
import com.gdev.geekacademybackend.repositories.StudentRepository;
import com.gdev.geekacademybackend.repositories.UserRepository;
import com.gdev.geekacademybackend.security.jwt.JwtUtils;
import com.gdev.geekacademybackend.security.services.UserDetailsImpl;
import com.gdev.geekacademybackend.services.dto.InstructorDto;
import com.gdev.geekacademybackend.services.dto.StaffDto;
import com.gdev.geekacademybackend.services.dto.StudentDto;
import com.gdev.geekacademybackend.services.dto.UserDto;
import com.gdev.geekacademybackend.services.payload.JwtResponse;
import com.gdev.geekacademybackend.services.payload.LoginRequest;
import com.gdev.geekacademybackend.services.payload.MessageResponse;
import com.gdev.geekacademybackend.services.payload.UserSummary;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public Collection<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        logger.info(authentication.getName());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

        return ResponseEntity
                .ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw (new AppException("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw (new AppException("Error: Email is already in use!"));
        }
        user.setPassword(encoder.encode(user.getPassword()));

        if (user instanceof Instructor) {// Create new instructor's account
            return instructorRepository.save((Instructor) user);
        } else if (user instanceof Student) {
            return studentRepository.save((Student) user);
        } else {// Create new staff's account
            return staffRepository.save((Staff) user);
        }
    }

    public void deleteUser(String login) {
        userRepository.findByUsername(login).ifPresent(user -> {
            userRepository.delete(user);
            logger.debug("Deleted User: {}", user);
        });
    }

    public List<Role> getAuthorities() {
        return roleRepository.findAll();
    }

    public void addRoleToUser(User user, RoleName roleName) {

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "name", roleName));

        user.addRole(role);
        userRepository.save(user);

    }

    public void addListRolesToUser(List<RoleName> roles, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        roles.stream().forEach(role -> {
            addRoleToUser(user, role);
        });

    }

    public void approveInstructor(String username) {
        Instructor instructor = getInstructor(username);
        instructor.setIsActivated(true);
        addRoleToUser(instructor, RoleName.INSTRUCTOR);

    }

    public void approvedStudent(String username) {
        Student student = getStudent(username);
        student.setIsActivated(true);
        addRoleToUser(student, RoleName.STUDENT);

    }

    public Student getStudent(String username) {
        Student student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "username", username));

        return student;

    }

    public Instructor getInstructor(String username) {
        Instructor instructor = instructorRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor", "username", username));

        return instructor;

    }

    public User getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        return user;

    }

    public UserSummary getUserSummary(Long id) {
        User user = getUser(id);
        return modelMapper.map(user, UserSummary.class);

    }

}
