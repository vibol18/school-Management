package com.example.school.dto;

import com.example.school.entity.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

    // =====================
    // STUDENT INFO
    // =====================

    private String firstName;

    private String lastName;

    private String phone;

    private Long classId;

    private Long courseId;

    private Role role;
}