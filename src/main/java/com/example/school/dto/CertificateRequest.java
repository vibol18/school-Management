package com.example.school.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificateRequest {

    private String certificateNo;

    private LocalDate issueDate;

    private String grade;

    private String remark;

    private Long studentId;

    private Long courseId;
}