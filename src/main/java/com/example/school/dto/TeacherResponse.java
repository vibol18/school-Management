package com.example.school.dto;

import java.math.BigDecimal;
import java.util.List;

import com.example.school.entity.ClassRoom;

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
    private String departmentName;
    private List<ClassRoom> classes;
}