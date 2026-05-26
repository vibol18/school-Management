package com.example.school.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeRequest {
    private Long studentId;

    private Long examId;

    private Double score;

    private String grade;

    private String remark;
}
