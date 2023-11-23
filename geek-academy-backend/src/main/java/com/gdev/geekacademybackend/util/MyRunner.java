package com.gdev.geekacademybackend.util;

import com.gdev.geekacademybackend.exceptions.ResourceNotFoundException;
import com.gdev.geekacademybackend.models.RoleName;
import com.gdev.geekacademybackend.models.Subject;
import com.gdev.geekacademybackend.models.User;
import com.gdev.geekacademybackend.repositories.RoleRepository;
import com.gdev.geekacademybackend.repositories.SubjectRespository;
import com.gdev.geekacademybackend.repositories.UserRepository;
import com.gdev.geekacademybackend.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SubjectRespository subjectRespository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        /*
          roleRepository.save(new Role(RoleName.INSTRUCTOR));
          roleRepository.save(new Role(RoleName.USER));
          roleRepository.save(new Role(RoleName.PLANNER));
          roleRepository.save(new Role(RoleName.INSTRUCTORS_MANAGER));
          roleRepository.save(new Role(RoleName.STUDENTS_MANAGER));
          roleRepository.save(new Role(RoleName.INSTRUCTORS_MANAGER));
          roleRepository.save(new Role(RoleName.STAFF_MANAGER));
          roleRepository.save(new Role(RoleName.ADMIN));
          roleRepository.save(new Role(RoleName.STUDENT));
          
          
          subjectRespository.save(new Subject("Math"));
          subjectRespository.save(new Subject("ASD"));
          subjectRespository.save(new Subject("Linear Algebra"));
          subjectRespository.save(new Subject("English"));
          subjectRespository.save(new Subject("Chemistry"));
          subjectRespository.save(new Subject("physics"));
    
          User user = userRepository.findByUsername("mohammed")
          .orElseThrow(() -> new ResourceNotFoundException("User", "username",
          "mohammed"));
          
          userService.addRoleToUser(user, RoleName.ADMIN);
          */
     

    }
}