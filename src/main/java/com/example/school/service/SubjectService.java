package com.example.school.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.school.dto.SubjectRequest;
import com.example.school.dto.SubjectResponse;
import com.example.school.entity.Course;
import com.example.school.entity.Subject;
import com.example.school.entity.Teacher;
import com.example.school.repository.CourseRepository;
import com.example.school.repository.SubjectRepository;
import com.example.school.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    // GET ALL
    public List<SubjectResponse> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    // GET ONE
    public SubjectResponse getSubjectById(Long id) {
        return map(findById(id));
    }

    // CREATE
    public SubjectResponse createSubject(SubjectRequest request) {

        Subject subject = new Subject();
        subject.setName(request.getName());
        subject.setDescription(request.getDescription());

        if (request.getCourseId() != null) {
            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            subject.setCourse(course);
        }

        // SET TEACHER
        if (request.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(request.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            subject.setTeacher(teacher);
        }

        return map(subjectRepository.save(subject));
    }

    // UPDATE
    public SubjectResponse updateSubject(Long id, SubjectRequest request) {

        Subject subject = findById(id);

        subject.setName(request.getName());
        subject.setDescription(request.getDescription());

        if (request.getCourseId() != null) {
            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            subject.setCourse(course);
        } else {
            subject.setCourse(null);
        }

        // UPDATE TEACHER
        if (request.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(request.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            subject.setTeacher(teacher);
        } else {
            subject.setTeacher(null);
        }

        return map(subjectRepository.save(subject));
    }

    // DELETE
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    // ── private helpers ──────────────────────────────────
    private Subject findById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }

    private SubjectResponse map(Subject s) {

        SubjectResponse res = new SubjectResponse();

        res.setId(s.getId());
        res.setName(s.getName());
        res.setDescription(s.getDescription());

        if (s.getCourse() != null) {
            res.setCourseId(s.getCourse().getId());
            res.setCourseName(s.getCourse().getName());
        }

        if (s.getTeacher() != null) {
            res.setTeacherId(s.getTeacher().getId());
            res.setTeacherName(
                    s.getTeacher().getFirstName() + " " + s.getTeacher().getLastName());
        }

        return res;
    }
}