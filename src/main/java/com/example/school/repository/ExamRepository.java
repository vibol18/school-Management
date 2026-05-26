package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

}