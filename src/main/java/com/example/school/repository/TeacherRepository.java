package com.example.school.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByEmail(String email);

    boolean existsByTeacherCode(String teacherCode);
}
