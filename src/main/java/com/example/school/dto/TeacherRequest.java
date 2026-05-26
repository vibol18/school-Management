package com.example.school.dto;

import java.math.BigDecimal;
import java.util.List;

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
    private Long departmentId;
    private Long userId;
    private List<Long> classIds;
}