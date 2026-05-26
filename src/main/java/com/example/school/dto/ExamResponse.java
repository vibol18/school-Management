package com.example.school.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamResponse {

    private Long id;

    private String title;

    private Long subjectId;

    private String subjectName;

    private LocalDate examDate;
    private Long classRoomId;
    private String classRoomName;
    private Integer totalMarks;
}