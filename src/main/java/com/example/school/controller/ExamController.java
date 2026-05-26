package com.example.school.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.school.dto.ExamRequest;
import com.example.school.dto.ExamResponse;
import com.example.school.service.ExamService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public ExamResponse create(@RequestBody ExamRequest req) {
        return examService.CreateExam(req);
    }

    @GetMapping
    public List<ExamResponse> getAll() {
        return examService.getAllExam();
    }

    @GetMapping("/{id}")
    public ExamResponse getById(@PathVariable Long id) {
        return examService.ShowexamById(id);
    }

    @PutMapping("/{id}")
    public ExamResponse update(
            @PathVariable Long id,
            @RequestBody ExamRequest req) {

        return examService.updateExam(id, req);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        examService.DeleteExam(id);

        return "Exam deleted successfully";
    }

}