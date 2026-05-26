package com.example.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByClazz_Id(Long classId);

    List<Student> findByCourse_Id(Long courseId);
}
