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

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {

        private final ExamrpoSitory examrpoSitory;
        private final GradeRepository gradeRepository;
        private final StudentRepository studentRepository;

        @Override
        public Double getStudentAverage(Long studentId) {

                List<Grade> grades = gradeRepository.findByStudentId(studentId);

                return grades.stream()
                                .mapToDouble(Grade::getScore)
                                .average()
                                .orElse(0.0);
        }

        @Override
        public GradeResponse createGrdae(GradeRequest req) {

                Student stu = studentRepository.findById(req.getStudentId())
                                .orElseThrow(() -> new RuntimeException("Student Not Found"));

                Exam exam = examrpoSitory.findById(req.getExamId())
                                .orElseThrow(() -> new RuntimeException("Exam Not Found"));

                Grade g = new Grade();

                g.setStudent(stu);
                g.setExam(exam);
                g.setScore(req.getScore());
                g.setGrade(calculateGrade(req.getScore()));
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
        public GradeResponse getById(Long id) {

                Grade grade = gradeRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Grade Not Found"));

                return mapToResponse(grade);
        }

        @Override
        public GradeResponse updateGrade(Long id, GradeRequest req) {

                Grade g = gradeRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Grade Record Not Found"));

                Student stu = studentRepository.findById(req.getStudentId())
                                .orElseThrow(() -> new RuntimeException("Student Not Found"));

                Exam exam = examrpoSitory.findById(req.getExamId())
                                .orElseThrow(() -> new RuntimeException("Exam Not Found"));

                g.setStudent(stu);
                g.setExam(exam);
                g.setScore(req.getScore());
                g.setGrade(calculateGrade(req.getScore()));
                g.setRemark(req.getRemark());

                return mapToResponse(gradeRepository.save(g));
        }

        @Override
        public void deleteGrade(Long id) {

                Grade grade = gradeRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Grade Not Found"));

                gradeRepository.delete(grade);
        }

        private String calculateGrade(Double score) {

                if (score >= 90) {
                        return "A";
                } else if (score >= 80) {
                        return "B";
                } else if (score >= 70) {
                        return "C";
                } else if (score >= 60) {
                        return "D";
                } else {
                        return "F";
                }
        }

        private GradeResponse mapToResponse(Grade g) {

                GradeResponse gr = new GradeResponse();

                gr.setId(g.getId());

                if (g.getStudent() != null) {
                        gr.setStudentId(g.getStudent().getId());
                        gr.setStudentName(
                                        g.getStudent().getFirstName() + " " +
                                                        g.getStudent().getLastName());
                }

                if (g.getExam() != null) {
                        gr.setExamId(g.getExam().getId());
                        gr.setExamTitle(g.getExam().getTitle());
                }

                gr.setScore(g.getScore());
                gr.setGrade(g.getGrade());
                gr.setRemark(g.getRemark());

                return gr;
        }
}