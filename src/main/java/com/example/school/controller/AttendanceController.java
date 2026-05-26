package com.example.school.controller;

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

import com.example.school.dto.AttendanceRequest;
import com.example.school.dto.AttendanceResponse;
import com.example.school.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:5173")
public class AttendanceController {
    private final AttendanceService attendanceService;

    @GetMapping
    List<AttendanceResponse> showallAttendance() {
        return attendanceService.getAllAttendances();
    }

    @PostMapping
    public AttendanceResponse create(@RequestBody AttendanceRequest req) {
        return attendanceService.createAttendance(req);
    }

    @GetMapping("/{id}")
    public AttendanceResponse getAttendancebyId(@PathVariable Long id) {
        return attendanceService.getById(id);
    }

    @PutMapping("/{id}")
    public AttendanceResponse updateAttendance(
            @PathVariable Long id,
            @RequestBody AttendanceRequest request) {
        return attendanceService.updateAttendance(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
    }
}
