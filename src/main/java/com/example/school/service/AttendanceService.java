package com.example.school.service;

import java.util.List;

import com.example.school.dto.AttendanceRequest;
import com.example.school.dto.AttendanceResponse;

public interface AttendanceService {

        AttendanceResponse createAttendance(
                        AttendanceRequest request);

        List<AttendanceResponse> getAllAttendances();

        AttendanceResponse updateAttendance(
                        Long id,
                        AttendanceRequest request);

        AttendanceResponse getById(Long id);

        void deleteAttendance(Long id);

        List<AttendanceResponse> getMonthlyAttendanceReport(int month, int year);

        AttendanceResponse markAllPresent(Long subjectId);
}