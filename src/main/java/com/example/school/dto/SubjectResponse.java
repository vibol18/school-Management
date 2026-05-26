package com.example.school.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectResponse {

    private Long id;
    private String name;
    private String description;

    private Long courseId;
    private String courseName;

    private Long teacherId;
    private String teacherName;
}