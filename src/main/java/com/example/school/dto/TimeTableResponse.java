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

    private Long teacherId;
    private String teacherName; // Added for UI convenience

    private LocalTime startTime;
    private LocalTime endTime;
    private String room;
}