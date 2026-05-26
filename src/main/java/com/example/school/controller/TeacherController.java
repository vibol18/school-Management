package com.example.school.controller;

import com.example.school.repository.TeacherRepository;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.school.dto.TeacherRequest;
import com.example.school.dto.TeacherResponse;
import com.example.school.service.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    public TeacherResponse createTeacher(@RequestBody TeacherRequest request) {
        return teacherService.createTeacher(request);
    }

    @GetMapping
    public List<TeacherResponse> showAllTeacher() {
        return teacherService.showAllTeachers();
    }

    @PutMapping("/{id}")
    public TeacherResponse updateteacher(
            @PathVariable Long id, @RequestBody TeacherRequest request) {
        return teacherService.updateTeacher(id, request);
    }

    @DeleteMapping("/{id}")
    public String DeleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return "delete Suceessfully";
    }
}
