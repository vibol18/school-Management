package com.example.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classes")
@Getter
@Setter
@NoArgsConstructor
public class ClassRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String roomNumber;
    private String section;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonIgnoreProperties({ "subjects", "classes", "user", "department" })

    private Teacher teacher;

    @JsonIgnore
    @OneToMany(mappedBy = "clazz")
    private List<Student> students = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<Subject> subjects = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "class_teachers", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    @JsonIgnore
    private List<Teacher> teachers = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "classRoom", cascade = CascadeType.ALL)
    private List<Exam> exams = new ArrayList<>();
}