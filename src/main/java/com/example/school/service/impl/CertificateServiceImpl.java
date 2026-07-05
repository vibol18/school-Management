package com.example.school.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.school.dto.CertificateRequest;
import com.example.school.dto.CertificateResponse;
import com.example.school.entity.Certificate;
import com.example.school.entity.Course;
import com.example.school.entity.Student;
import com.example.school.repository.CertificateRepository;
import com.example.school.repository.CourseRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.service.CertificateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public CertificateResponse createCertificate(CertificateRequest request) {

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Certificate certificate = new Certificate();

        certificate.setCertificateNo(request.getCertificateNo());
        certificate.setIssueDate(request.getIssueDate());
        certificate.setGrade(request.getGrade());
        certificate.setRemark(request.getRemark());

        certificate.setStudent(student);
        certificate.setCourse(course);

        Certificate savedCertificate = certificateRepository.save(certificate);
        return mapToResponse(savedCertificate);
    }

    @Override
    public List<CertificateResponse> getAllCertificate() {

        return certificateRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CertificateResponse getByID(Long id) {

        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));

        return mapToResponse(certificate);
    }

    @Override
    public CertificateResponse updateCertificate(Long id, CertificateRequest request) {

        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        certificate.setCertificateNo(request.getCertificateNo());
        certificate.setIssueDate(request.getIssueDate());
        certificate.setGrade(request.getGrade());
        certificate.setRemark(request.getRemark());

        certificate.setStudent(student);
        certificate.setCourse(course);

        return mapToResponse(certificateRepository.save(certificate));
    }

    @Override
    public void deleteCertificate(Long id) {
        certificateRepository.deleteById(id);
    }

    private CertificateResponse mapToResponse(Certificate c) {

        CertificateResponse res = new CertificateResponse();

        res.setId(c.getId());
        res.setCertificateNo(c.getCertificateNo());
        res.setIssueDate(c.getIssueDate());
        res.setGrade(c.getGrade());
        res.setRemark(c.getRemark());

        if (c.getStudent() != null) {
            res.setStudentId(c.getStudent().getId());
            res.setStudentName(
                    c.getStudent().getFirstName() + " " +
                            c.getStudent().getLastName());
        }

        if (c.getCourse() != null) {
            res.setCourseId(c.getCourse().getId());
            res.setCourseName(c.getCourse().getName());
        }

        return res;
    }
}