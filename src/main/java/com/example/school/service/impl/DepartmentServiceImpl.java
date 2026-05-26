package com.example.school.service.impl;

import com.example.school.dto.DepartmentRequest;
import com.example.school.dto.DepartmentResponse;
import com.example.school.entity.Department;

import com.example.school.repository.DepartmentRepository;

import com.example.school.service.DepartmentService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl
        implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponse createDepartment(
            DepartmentRequest request) {

        if (departmentRepository.existsByname(
                request.getName())) {
            throw new RuntimeException(
                    "Department already exists");
        }

        Department department = new Department();

        department.setName(request.getName());
        department.setDescription(
                request.getDescription());

        Department savedDepartment = departmentRepository.save(
                department);

        return mapToResponse(savedDepartment);
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse getDepartmentById(
            Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Department not found"));

        return mapToResponse(department);
    }

    @Override
    public DepartmentResponse updateDepartment(
            Long id,
            DepartmentRequest request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Department not found"));

        department.setName(request.getName());
        department.setDescription(
                request.getDescription());

        Department updatedDepartment = departmentRepository.save(
                department);

        return mapToResponse(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Department not found"));

        departmentRepository.delete(department);
    }

    private DepartmentResponse mapToResponse(
            Department department) {

        DepartmentResponse response = new DepartmentResponse();

        response.setId(department.getId());

        response.setName(department.getName());

        response.setDescription(
                department.getDescription());

        return response;
    }
}