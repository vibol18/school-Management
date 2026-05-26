package com.example.school.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectRequest {

    private String name;
    private String description;
    private Long courseId;
    private Long teacherId;
}