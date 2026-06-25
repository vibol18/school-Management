package com.example.school.dto;

import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeTableReq {
    private Long classId;
    private Long courseId;
    private Long teacherId; // This must match req.getTeacherId()
    private String dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String room;
}