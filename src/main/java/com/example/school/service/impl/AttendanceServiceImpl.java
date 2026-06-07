package com.example.school.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.school.dto.AttendanceRequest;
import com.example.school.dto.AttendanceResponse;
import com.example.school.entity.Attendance;
import com.example.school.entity.Student;
import com.example.school.entity.Subject;
import com.example.school.entity.enums.AttendanceStatus;
import com.example.school.repository.AttendanceRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.repository.SubjectRepository;
import com.example.school.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public AttendanceResponse createAttendance(AttendanceRequest req) {

        try {

            Student student = studentRepository.findById(req.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));

            Subject subject = subjectRepository.findById(req.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found"));

            Attendance attendance = new Attendance();

            attendance.setStudent(student);
            attendance.setSubject(subject);
            attendance.setDate(LocalDate.parse(req.getDate().trim()));
            attendance.setStatus(AttendanceStatus.valueOf(req.getStatus()));
            attendance.setRemark(req.getRemark());

            return map(attendanceRepository.save(attendance));

        } catch (Exception e) {

            e.printStackTrace();

            throw e;
        }
    }

    @Override
    public List<AttendanceResponse> getAllAttendances() {
        return attendanceRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public AttendanceResponse markAllPresent(Long subjectId) {

        List<Student> students = studentRepository.findAll();

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        AttendanceResponse response = new AttendanceResponse();

        for (Student student : students) {

            Attendance attendance = new Attendance();

            attendance.setStudent(student);
            attendance.setSubject(subject);
            attendance.setDate(LocalDate.now());
            attendance.setStatus(AttendanceStatus.PRESENT);
            attendance.setRemark("Marked automatically");

            attendanceRepository.save(attendance);

            response.setSubjectId(subject.getId());
            response.setSubjectName(subject.getName());
        }

        return response;
    }

    @Override
    public List<AttendanceResponse> getMonthlyAttendanceReport(int month, int year) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        return attendanceRepository.findByDateBetween(startDate, endDate)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public AttendanceResponse getById(Long id) {

        Attendance a = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        return map(a);
    }

    @Override
    public AttendanceResponse updateAttendance(Long id, AttendanceRequest req) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attendance not found"));

        Student student = studentRepository.findById(req.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Subject subject = subjectRepository.findById(req.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        attendance.setStudent(student);
        attendance.setSubject(subject);
        attendance.setDate(LocalDate.parse(req.getDate()));
        attendance.setStatus(AttendanceStatus.valueOf(req.getStatus()));
        attendance.setRemark(req.getRemark());

        return map(attendanceRepository.save(attendance));
    }

    @Override
    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }

    private AttendanceResponse map(Attendance a) {

        AttendanceResponse res = new AttendanceResponse();

        res.setId(a.getId());

        if (a.getStudent() != null) {
            res.setStudentId(a.getStudent().getId());
            res.setStudentName(a.getStudent().getFirstName() + " " + a.getStudent().getLastName());
        }

        if (a.getSubject() != null) {
            res.setSubjectId(a.getSubject().getId());
            res.setSubjectName(a.getSubject().getName());
        }

        res.setDate(a.getDate());
        res.setStatus(a.getStatus());
        res.setRemark(a.getRemark());

        return res;
    }
}