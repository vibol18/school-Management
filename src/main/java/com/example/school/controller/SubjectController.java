package com.example.school.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.school.dto.SubjectRequest;
import com.example.school.dto.SubjectResponse;
import com.example.school.service.SubjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class SubjectController {

    private final SubjectService subjectService;

    // GET ALL
    @GetMapping
    public List<SubjectResponse> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    // GET ONE
    @GetMapping("/{id}")
    public SubjectResponse getSubject(
            @PathVariable Long id) {

        return subjectService.getSubjectById(id);
    }

    // CREATE
    @PostMapping
    public SubjectResponse createSubject(
            @RequestBody SubjectRequest request) {

        return subjectService.createSubject(request);
    }

    // UPDATE
    @PutMapping("/{id}")
    public SubjectResponse updateSubject(
            @PathVariable Long id,
            @RequestBody SubjectRequest request) {

        return subjectService.updateSubject(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteSubject(
            @PathVariable Long id) {

        subjectService.deleteSubject(id);

        return "Subject deleted successfully";
    }
}