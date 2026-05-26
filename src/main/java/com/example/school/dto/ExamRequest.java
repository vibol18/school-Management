package com.example.school.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamRequest {

    private String title;

    private Long subjectId;
    private LocalDate examDate;
    private Long classRoomId;
    private Integer totalMarks;
}