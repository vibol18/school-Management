package com.example.school.service;

import java.util.List;

import com.example.school.dto.GradeRequest;
import com.example.school.dto.GradeResponse;

public interface GradeService {
    GradeResponse createGrdae(GradeRequest req);

    List<GradeResponse> getAllGrade();

    GradeResponse getById(Long id);

    GradeResponse updateGrade(Long id, GradeRequest req);

    void deleteGrade(Long id);

    Double getStudentAverage(Long studentId);
}
