package com.example.school.entity;

import java.time.LocalTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "time_tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // RELATIONSHIPS
    // =========================

    @ManyToOne
    @JoinColumn(name = "teacher_id") // Changed from Long teacherId to a proper relationship
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassRoom clazz;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // =========================
    // FIELDS
    // =========================

    private String dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private String room;
}