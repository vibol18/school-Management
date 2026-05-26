package com.example.school.service;

import java.util.List;

import com.example.school.dto.DepartmentRequest;
import com.example.school.dto.DepartmentResponse;

public interface DepartmentService {

        DepartmentResponse createDepartment(
                        DepartmentRequest request);

        List<DepartmentResponse> getAllDepartments();

        DepartmentResponse getDepartmentById(
                        Long id);

        DepartmentResponse updateDepartment(
                        Long id,
                        DepartmentRequest request);

        void deleteDepartment(Long id);
}