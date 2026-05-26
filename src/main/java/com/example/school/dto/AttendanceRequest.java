package com.example.school.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceRequest {
    private Long studentId;
    private Long subjectId;
    private String date;
    private String status;
    private String remark;
}