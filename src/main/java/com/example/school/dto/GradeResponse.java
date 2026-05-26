package com.example.school.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeResponse {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long examId;
    private String examTitle;
    private Double score;
    private String grade;
    private String remark;
}
