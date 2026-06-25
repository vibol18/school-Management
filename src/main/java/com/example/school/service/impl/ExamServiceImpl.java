package com.example.school.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.school.dto.ExamRequest;
import com.example.school.dto.ExamResponse;
import com.example.school.entity.ClassRoom;
import com.example.school.entity.Exam;
import com.example.school.entity.Subject;
import com.example.school.repository.ClassRoomrepository;
import com.example.school.repository.ExamRepository;
import com.example.school.repository.SubjectRepository;
import com.example.school.service.ExamService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final SubjectRepository subjectRepository;
    private final ClassRoomrepository classRoomrepository;

    @Override
    public ExamResponse CreateExam(ExamRequest req) {
        Subject subject = subjectRepository.findById(req.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        ClassRoom classRoom = classRoomrepository.findById(req.getClassRoomId())
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        Exam exam = new Exam();
        exam.setTitle(req.getTitle());
        exam.setSubject(subject);
        exam.setClassRoom(classRoom);
        exam.setExamDate(req.getExamDate());
        exam.setTotalMarks(req.getTotalMarks());

        return mapToResponse(examRepository.save(exam));
    }

    @Override
    public List<ExamResponse> getAllExam() {

        return examRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ExamResponse ShowexamById(Long id) {

        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        return mapToResponse(exam);
    }

    public ExamResponse updateExam(Long id, ExamRequest req) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        Subject subject = subjectRepository.findById(req.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        ClassRoom classRoom = classRoomrepository.findById(req.getClassRoomId())
                .orElseThrow(() -> new RuntimeException("Classroom not found"));

        exam.setTitle(req.getTitle());
        exam.setSubject(subject);
        exam.setClassRoom(classRoom);
        exam.setExamDate(req.getExamDate());
        exam.setTotalMarks(req.getTotalMarks());

        return mapToResponse(examRepository.save(exam));
    }

    public void DeleteExam(Long id) {

        examRepository.deleteById(id);
    }

    private ExamResponse mapToResponse(Exam exam) {
        ExamResponse res = new ExamResponse();
        res.setId(exam.getId());
        res.setTitle(exam.getTitle());
        res.setSubjectId(exam.getSubject().getId());
        res.setSubjectName(exam.getSubject().getName());
        if (exam.getClassRoom() != null) {
            res.setClassRoomId(exam.getClassRoom().getId());
            res.setClassRoomName(exam.getClassRoom().getName());
        }
        res.setExamDate(exam.getExamDate());
        res.setTotalMarks(exam.getTotalMarks());
        return res;
    }
}