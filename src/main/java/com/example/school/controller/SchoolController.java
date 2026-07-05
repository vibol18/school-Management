package com.example.school.controller;

import com.example.school.dto.SchoolReq;
import com.example.school.dto.SchoolResponse;
import com.example.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;

    @PostMapping
    public ResponseEntity<SchoolResponse> createSchool(@RequestBody SchoolReq request) {
        return ResponseEntity.ok(schoolService.createSchool(request));
    }

    @GetMapping
    public ResponseEntity<List<SchoolResponse>> getAllSchools() {
        return ResponseEntity.ok(schoolService.getAllSchools());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolResponse> getSchoolById(@PathVariable Long id) {
        return ResponseEntity.ok(schoolService.getSchoolById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolResponse> updateSchool(@PathVariable Long id, @RequestBody SchoolReq request) {
        return ResponseEntity.ok(schoolService.updateSchool(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }
}
