package com.gdev.geekacademybackend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;

import com.gdev.geekacademybackend.models.Subject;
import com.gdev.geekacademybackend.services.SubjectService;
import com.gdev.geekacademybackend.services.dto.SubjectDto;
import com.gdev.geekacademybackend.services.payload.ApiResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<SubjectDto> getAllSubjects() {

        return subjectService.getAllSubjects().stream().map(subject -> modelMapper.map(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable(name = "id") Long id) {
        Subject subject = subjectService.getSubjectById(id);

        // convert entity to DTO
        SubjectDto subjectResponse = modelMapper.map(subject, SubjectDto.class);

        return ResponseEntity.ok().body(subjectResponse);
    }

    @PostMapping
    @RolesAllowed({ "ADMIN" })
    public ResponseEntity<SubjectDto> createSubject(@RequestBody SubjectDto subjectDto) {

        // convert DTO to entity
        Subject subjectRequest = modelMapper.map(subjectDto, Subject.class);

        Subject subject = subjectService.createSubject(subjectRequest);

        // convert entity to DTO
        SubjectDto subjectResponse = modelMapper.map(subject, SubjectDto.class);

        return new ResponseEntity<SubjectDto>(subjectResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<SubjectDto> updateSubject(@PathVariable long id, @RequestBody SubjectDto subjectDto) {

        // convert DTO to Entity
        Subject subjectRequest = modelMapper.map(subjectDto, Subject.class);

        Subject subject = subjectService.updateSubject(id, subjectRequest);

        // entity to DTO
        SubjectDto subjectResponse = modelMapper.map(subject, SubjectDto.class);

        return ResponseEntity.ok().body(subjectResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSubject(@PathVariable(name = "id") Long id) {
        subjectService.deleteSubject(id);
        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Subject deleted successfully", HttpStatus.OK);
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }

}
