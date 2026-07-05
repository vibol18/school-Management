package com.example.school.service.impl;

import com.example.school.dto.SchoolReq;
import com.example.school.dto.SchoolResponse;
import com.example.school.entity.School;
import com.example.school.repository.SchoolRepository;
import com.example.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Override
    public SchoolResponse createSchool(SchoolReq request) {
        if (schoolRepository.existsByName(request.getName())) {
            throw new RuntimeException("School already exists");
        }

        School school = new School();
        school.setName(request.getName());
        school.setAddress(request.getAddress());

        School savedSchool = schoolRepository.save(school);
        return mapToResponse(savedSchool);
    }

    @Override
    public List<SchoolResponse> getAllSchools() {
        return schoolRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public SchoolResponse getSchoolById(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));
        return mapToResponse(school);
    }

    @Override
    public SchoolResponse updateSchool(Long id, SchoolReq request) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));

        school.setName(request.getName());
        school.setAddress(request.getAddress());

        return mapToResponse(schoolRepository.save(school));
    }

    @Override
    public void deleteSchool(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School not found"));
        schoolRepository.delete(school);
    }

    private SchoolResponse mapToResponse(School school) {
        SchoolResponse response = new SchoolResponse();
        response.setId(school.getId());
        response.setName(school.getName());
        response.setAddress(school.getAddress());
        return response;
    }
}
