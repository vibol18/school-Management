package com.example.school.dto;

import java.time.LocalDate;
import com.example.school.entity.enums.AttendanceStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttendanceResponse {
    private Long id;
    private LocalDate attendanceDate;

    private Long studentId;
    private String studentName;

    private Long subjectId;
    private String subjectName;

    private LocalDate date;
    private AttendanceStatus status;
    private String remark;
}