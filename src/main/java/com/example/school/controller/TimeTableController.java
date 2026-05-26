package com.example.school.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.school.dto.TimeTableReq;
import com.example.school.dto.TimeTableResponse;
import com.example.school.service.TimeTableService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/timetable")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class TimeTableController {

    private final TimeTableService timeTableService;

    @PostMapping
    public TimeTableResponse create(
            @RequestBody TimeTableReq request) {
        return timeTableService.create(request);
    }

    @GetMapping
    public List<TimeTableResponse> getAll() {
        return timeTableService.getAll();
    }

    @GetMapping("/{id}")
    public TimeTableResponse getById(
            @PathVariable Long id) {
        return timeTableService.getById(id);
    }

    @PutMapping("/{id}")
    public TimeTableResponse update(
            @PathVariable Long id,
            @RequestBody TimeTableReq request) {
        return timeTableService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {
        timeTableService.delete(id);
    }
}