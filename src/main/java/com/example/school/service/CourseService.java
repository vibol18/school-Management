package com.example.school.service;

import java.util.List;

import com.example.school.dto.CourseRequest;
import com.example.school.dto.CourseResponse;

public interface CourseService {
    CourseResponse create(CourseRequest req);

    List<CourseResponse> getAllCourse();

    CourseResponse updateCourse(Long id, CourseRequest req);

    CourseResponse getById(Long id);

    void deleteCourse(Long id);
}
