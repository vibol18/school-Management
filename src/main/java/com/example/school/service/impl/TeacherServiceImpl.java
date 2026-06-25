package com.example.school.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.school.dto.TeacherRequest;
import com.example.school.dto.TeacherResponse;
import com.example.school.entity.Department;
import com.example.school.entity.Teacher;
import com.example.school.repository.DepartmentRepository;
import com.example.school.repository.TeacherRepository;
import com.example.school.service.TeacherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public TeacherResponse createTeacher(TeacherRequest request) {

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Teacher teacher = new Teacher();

        teacher.setTeacherCode(request.getTeacherCode());
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setSalary(request.getSalary());
        teacher.setEmploymentType(request.getEmploymentType());
        teacher.setExperienceYears(request.getExperienceYears());
        teacher.setDepartment(department);

        Teacher saved = teacherRepository.save(teacher);

        return mapToResponse(saved);
    }

    @Override
    public List<TeacherResponse> showAllTeachers() {

        return teacherRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherResponse showTeacherById(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        return mapToResponse(teacher);
    }

    @Override
    public TeacherResponse updateTeacher(Long id, TeacherRequest request) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        teacher.setTeacherCode(request.getTeacherCode());
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setSalary(request.getSalary());
        teacher.setEmploymentType(request.getEmploymentType());
        teacher.setExperienceYears(request.getExperienceYears());
        teacher.setDepartment(department);

        return mapToResponse(teacherRepository.save(teacher));
    }

    @Override
    public void deleteTeacher(Long id) {

        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacherRepository.delete(teacher);
    }

    private TeacherResponse mapToResponse(Teacher teacher) {

        TeacherResponse res = new TeacherResponse();

        res.setId(teacher.getId());
        res.setTeacherCode(teacher.getTeacherCode());
        res.setFirstName(teacher.getFirstName());
        res.setLastName(teacher.getLastName());
        res.setEmail(teacher.getEmail());
        res.setSalary(teacher.getSalary());
        res.setEmploymentType(teacher.getEmploymentType());
        res.setExperienceYears(teacher.getExperienceYears());

        if (teacher.getDepartment() != null) {
            res.setDepartmentName(teacher.getDepartment().getName());
        }

        return res;
    }
}