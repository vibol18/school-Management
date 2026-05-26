package com.example.school.dto;

import java.time.LocalDate;

import com.example.school.entity.enums.Gender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequest {

    private String studentCode;

    private String firstName;

    private String lastName;

    private Gender gender;

    private LocalDate dateOfBirth;

    private String phone;

    private String address;

    private String photo;

    private Long classId;

    private Long courseId;
}