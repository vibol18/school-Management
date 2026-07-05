package com.example.school.service;

import com.example.school.dto.SchoolReq;
import com.example.school.dto.SchoolResponse;

import java.util.List;

public interface SchoolService {
    SchoolResponse createSchool(SchoolReq request);

    List<SchoolResponse> getAllSchools();

    SchoolResponse getSchoolById(Long id);

    SchoolResponse updateSchool(Long id, SchoolReq request);

    void deleteSchool(Long id);
}
