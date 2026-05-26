package com.example.school.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.school.dto.CourseRequest;
import com.example.school.dto.CourseResponse;
import com.example.school.entity.Course;
import com.example.school.entity.Subject;
import com.example.school.repository.CourseRepository;
import com.example.school.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseResponse create(CourseRequest req) {

        Course course = new Course();

        course.setName(req.getName());
        course.setImageUrl(req.getImageUrl());
        course.setLevel(req.getLevel());
        course.setDuration(req.getDuration());
        course.setPrice(req.getPrice());
        course.setDescription(req.getDescription());
        course.setActive(req.getActive());

        return mapToResponse(courseRepository.save(course));
    }

    @Override
    public List<CourseResponse> getAllCourse() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponse getById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return mapToResponse(course);
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest req) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setName(req.getName());
        course.setImageUrl(req.getImageUrl());
        course.setLevel(req.getLevel());
        course.setDuration(req.getDuration());
        course.setPrice(req.getPrice());
        course.setDescription(req.getDescription());
        course.setActive(req.getActive());

        return mapToResponse(courseRepository.save(course));
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    private CourseResponse mapToResponse(Course course) {

        CourseResponse res = new CourseResponse();

        res.setId(course.getId());
        res.setName(course.getName());
        res.setImageUrl(course.getImageUrl());
        res.setLevel(course.getLevel());
        res.setDuration(course.getDuration());
        res.setPrice(course.getPrice());
        res.setDescription(course.getDescription());
        res.setActive(course.getActive());

        // SAFE null check (IMPORTANT)
        res.setSubjects(
                course.getSubjects() == null ? List.of()
                        : course.getSubjects()
                                .stream()
                                .map(Subject::getName)
                                .collect(Collectors.toList()));

        return res;
    }
}