package com.example.school.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_exams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentExam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer score;

    private Boolean passed;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
}