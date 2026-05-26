package com.example.school.dto;

import com.example.school.entity.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponse {

    private Long id;

    private String studentCode;

    private String firstName;

    private String lastName;

    private Gender gender;

    // CLASS
    private Long classId;

    private String className;
}
