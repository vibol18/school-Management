package com.example.school.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequest {
    private String name;
    private String imageUrl;
    private String level;
    private String duration;
    private Double price;
    private String description;
    private Boolean active;
}
