package com.example.school.dto;

import java.math.BigDecimal;
import java.util.List;

import com.example.school.entity.ClassRoom;
import com.example.school.entity.EmploymentType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherResponse {
    private Long id;
    private String teacherCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private BigDecimal salary;
    private EmploymentType employmentType;
    private Integer experienceYears;
    private String departmentName;
    private List<ClassRoom> classes;
}