package com.example.school.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.school.dto.GradeRequest;
import com.example.school.dto.GradeResponse;
import com.example.school.entity.Exam;
import com.example.school.entity.Grade;
import com.example.school.entity.Student;
import com.example.school.repository.ExamrpoSitory;
import com.example.school.repository.GradeRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.service.GradeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {
    private final ExamrpoSitory examrpoSitory;
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    @Override
    public GradeResponse createGrdae(GradeRequest req) {
        Student stu = studentRepository.findById(req.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student Not Found"));
        Exam exam = examrpoSitory.findById(req.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not Found"));
        Grade g = new Grade();
        g.setStudent(stu);
        g.setExam(exam);
        g.setScore(req.getScore());
        g.setGrade(req.getGrade());
        g.setRemark(req.getRemark());
        return mapToResponse(gradeRepository.save(g));
    }

    @Override
    public List<GradeResponse> getAllGrade() {

        return gradeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GradeResponse updateGrade(Long id, GradeRequest req) {
        // 1. Find the existing grade record by its own ID
        Grade g = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade record not Found"));

        // 2. Lookup the correct associated entities using the IDs stored inside the
        // request object!
        Exam e = examrpoSitory.findById(req.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not Found"));

        Student s = studentRepository.findById(req.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not Found"));

        // 3. Bind new values safely
        g.setStudent(s);
        g.setExam(e);
        g.setScore(req.getScore());
        g.setRemark(req.getRemark());
        g.setGrade(req.getGrade());

        return mapToResponse(gradeRepository.save(g));
    }

    @Override
    public GradeResponse getById(Long id) {

        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        return mapToResponse(grade);
    }

    @Override
    public void deleteGrade(Long id) {

        gradeRepository.deleteById(id);
    }

    private GradeResponse mapToResponse(Grade g) {
        GradeResponse gr = new GradeResponse();
        gr.setId(g.getId());
        gr.setStudentId(g.getStudent().getId());
        gr.setStudentName(g.getStudent().getFirstName() + " " +
                g.getStudent().getLastName());
        gr.setExamId(g.getExam().getId());
        gr.setExamTitle(g.getExam().getTitle());
        gr.setScore(g.getScore());
        gr.setGrade(g.getGrade());
        gr.setRemark(g.getRemark());
        return gr;
    }
}