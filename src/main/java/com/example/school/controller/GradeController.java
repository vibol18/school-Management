package com.example.school.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.school.dto.GradeRequest;
import com.example.school.dto.GradeResponse;
import com.example.school.service.GradeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class GradeController {

    private final GradeService gradeService;

    @GetMapping("/student/{studentId}/average")
    public Double getStudentAverage(
            @PathVariable Long studentId) {

        return gradeService.getStudentAverage(studentId);
    }

    // CREATE
    @PostMapping
    public GradeResponse createGrade(@RequestBody GradeRequest req) {

        return gradeService.createGrdae(req);
    }

    @GetMapping
    public List<GradeResponse> getAllGrades() {

        return gradeService.getAllGrade();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public GradeResponse getGradeById(@PathVariable Long id) {

        return gradeService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public GradeResponse updateGrade(
            @PathVariable Long id,
            @RequestBody GradeRequest req) {

        return gradeService.updateGrade(id, req);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteGrade(@PathVariable Long id) {

        gradeService.deleteGrade(id);

        return "Grade deleted successfully";
    }
}