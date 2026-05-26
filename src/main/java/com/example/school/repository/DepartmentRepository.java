package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByname(String name);
}
