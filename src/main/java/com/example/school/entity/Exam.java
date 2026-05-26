package com.example.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exams")
@Getter
@Setter
@NoArgsConstructor
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private LocalDate examDate;
    private Integer duration;

    private Integer totalMarks;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    @JsonIgnoreProperties({ "exams", "students", "teachers", "subjects" })
    private ClassRoom classRoom;
}