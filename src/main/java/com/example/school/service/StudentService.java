package com.example.school.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.school.dto.StudentRequest;
import com.example.school.entity.ClassRoom;
import com.example.school.entity.Course;
import com.example.school.entity.Student;
import com.example.school.repository.ClassRoomrepository;
import com.example.school.repository.CourseRepository;
import com.example.school.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final ClassRoomrepository classRoomrepository;

    private final CourseRepository courseRepository;

    // =========================
    // GET ALL STUDENTS
    // =========================
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // =========================
    // GET STUDENT BY ID
    // =========================
    public Student getStudentById(Long id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // =========================
    // CREATE STUDENT
    // =========================
    public Student createStudent(StudentRequest request) {

        Student student = new Student();

        student.setStudentCode(request.getStudentCode());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setGender(request.getGender());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setPhoto(request.getPhoto());

        // SET CLASS
        if (request.getClassId() != null) {

            ClassRoom clazz = classRoomrepository.findById(request.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class not found"));

            student.setClazz(clazz);
        }

        // SET COURSE
        if (request.getCourseId() != null) {

            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            student.setCourse(course);
        }

        return studentRepository.save(student);
    }

    // =========================
    // UPDATE STUDENT
    // =========================
    public Student updateStudent(Long id, StudentRequest request) {

        Student student = getStudentById(id);

        student.setStudentCode(request.getStudentCode());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setGender(request.getGender());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setPhoto(request.getPhoto());

        // UPDATE CLASS
        if (request.getClassId() != null) {

            ClassRoom clazz = classRoomrepository.findById(request.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class not found"));

            student.setClazz(clazz);

        } else {

            student.setClazz(null);
        }

        // UPDATE COURSE
        if (request.getCourseId() != null) {

            Course course = courseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            student.setCourse(course);

        } else {

            student.setCourse(null);
        }

        return studentRepository.save(student);
    }

    // =========================
    // DELETE STUDENT
    // =========================
    public void deleteStudent(Long id) {

        studentRepository.deleteById(id);
    }

    // =========================
    // GET STUDENTS BY CLASS
    // =========================
    public List<Student> getStudentsByClass(Long classId) {

        return studentRepository.findByClazz_Id(classId);
    }

    // =========================
    // GET STUDENTS BY COURSE
    // =========================
    public List<Student> getStudentsByCourse(Long courseId) {

        return studentRepository.findByCourse_Id(courseId);
    }
}