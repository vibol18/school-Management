package com.example.school.service;

import java.util.List;

import com.example.school.dto.TeacherRequest;
import com.example.school.dto.TeacherResponse;

public interface TeacherService {

        TeacherResponse createTeacher(
                        TeacherRequest request);

        List<TeacherResponse> showAllTeachers();

        TeacherResponse showTeacherById(
                        Long id);

        TeacherResponse updateTeacher(
                        Long id,
                        TeacherRequest request);

        void deleteTeacher(Long id);
}