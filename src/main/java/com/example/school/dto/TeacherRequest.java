package com.example.school.dto;

import java.math.BigDecimal;
import java.util.List;

import com.example.school.entity.EmploymentType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequest {
    private String teacherCode;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal salary;
    private EmploymentType employmentType;
    private Integer experienceYears;
    private Long departmentId;
    private Long userId;
    private List<Long> classIds;
}