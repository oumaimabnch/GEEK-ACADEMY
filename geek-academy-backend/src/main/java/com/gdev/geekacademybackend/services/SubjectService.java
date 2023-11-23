package com.gdev.geekacademybackend.services;

import java.util.List;

import com.gdev.geekacademybackend.exceptions.ResourceNotFoundException;
import com.gdev.geekacademybackend.models.Subject;
import com.gdev.geekacademybackend.repositories.SubjectRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    @Autowired
    private SubjectRespository subjectRespository;

    public List<Subject> getAllSubjects() {
        return subjectRespository.findAll();
    }

    public Subject createSubject(Subject subject) {
        return subjectRespository.save(subject);
    }

    public Subject updateSubject(long id, Subject subjectRequest) {
        Subject subject = subjectRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));

        subject.setName(subjectRequest.getName());
        subject.setDescription(subjectRequest.getDescription());
        return subjectRespository.save(subject);
    }

    public void deleteSubject(long id) {
        Subject subject = subjectRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));

        subjectRespository.delete(subject);
    }

    public Subject getSubjectById(long id) {

        Subject subject = subjectRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));

        return subject;
    }

    public Subject getSubjectByName(String name) {

        Subject subject = subjectRespository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", "name", name));

        return subject;
    }
}
