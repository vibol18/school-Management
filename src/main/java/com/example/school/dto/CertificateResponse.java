package com.example.school.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificateResponse {

    private Long id;

    private String certificateNo;

    private LocalDate issueDate;

    private String grade;

    private String remark;

    // Student
    private Long studentId;
    private String studentName;

    // Course
    private Long courseId;
    private String courseName;
}