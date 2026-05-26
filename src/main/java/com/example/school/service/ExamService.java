package com.example.school.service;

import java.util.List;

import com.example.school.dto.ExamRequest;
import com.example.school.dto.ExamResponse;

public interface ExamService {
    ExamResponse CreateExam(ExamRequest request);

    List<ExamResponse> getAllExam();

    ExamResponse ShowexamById(Long id);

    ExamResponse updateExam(Long id, ExamRequest req);

    void DeleteExam(Long id);

}
