package com.example.school.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeTableResponse {
    private Long id;

    private Long classId;
    private String className;

    private Long courseId;
    private String courseName;

    private String dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private String room;
}
