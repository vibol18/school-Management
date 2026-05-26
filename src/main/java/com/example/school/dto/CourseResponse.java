package com.example.school.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CourseResponse {
    private Long id;
    private String name;
    private String imageUrl;
    private String level;
    private String duration;
    private Double price;
    private String description;
    private Boolean active;

    private List<String> subjects;
}