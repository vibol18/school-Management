package com.example.school.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.school.dto.TimeTableReq;
import com.example.school.dto.TimeTableResponse;
import com.example.school.entity.ClassRoom;
import com.example.school.entity.Course;
import com.example.school.entity.Teacher;
import com.example.school.entity.TimeTable;
import com.example.school.repository.ClassRoomrepository;
import com.example.school.repository.CourseRepository;
import com.example.school.repository.TeacherRepository;
import com.example.school.repository.TimeTableRepository;
import com.example.school.service.TimeTableService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeTableServiceImpl implements TimeTableService {
    private final TimeTableRepository timeTableRepository;
    private final ClassRoomrepository classRoomrepository; // Keep matching your exact repo interface name
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public TimeTableResponse create(TimeTableReq req) {
        ClassRoom clazz = classRoomrepository.findById(req.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));
        Course course = courseRepository.findById(req.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Teacher teacher = teacherRepository.findById(req.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        validateNoConflicts(null, req);

        TimeTable timeTable = new TimeTable();
        timeTable.setCourse(course);
        timeTable.setClazz(clazz);
        timeTable.setDayOfWeek(req.getDayOfWeek());
        timeTable.setStartTime(req.getStartTime());
        timeTable.setEndTime(req.getEndTime());
        timeTable.setRoom(req.getRoom());
        timeTable.setTeacher(teacher);

        return mapToResponse(timeTableRepository.save(timeTable));
    }

    @Override
    public List<TimeTableResponse> getAll() {
        return timeTableRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TimeTableResponse getById(Long id) {
        TimeTable timeTable = timeTableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TimeTable Not Found"));
        return mapToResponse(timeTable);
    }

    @Override
    public TimeTableResponse update(Long id, TimeTableReq req) {
        TimeTable timeTable = timeTableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TimeTable not found"));
        ClassRoom clazz = classRoomrepository.findById(req.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));
        Course course = courseRepository.findById(req.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Teacher teacher = teacherRepository.findById(req.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        // Prevent double booking (passing current ID so it ignores itself)
        validateNoConflicts(id, req);

        timeTable.setClazz(clazz);
        timeTable.setCourse(course);
        timeTable.setTeacher(teacher);
        timeTable.setDayOfWeek(req.getDayOfWeek());
        timeTable.setStartTime(req.getStartTime());
        timeTable.setEndTime(req.getEndTime());
        timeTable.setRoom(req.getRoom());

        return mapToResponse(timeTableRepository.save(timeTable));
    }

    @Override
    public void delete(Long id) {
        timeTableRepository.deleteById(id);
    }

    private void validateNoConflicts(Long id, TimeTableReq req) {
        boolean isConflict = timeTableRepository.hasScheduleConflict(
                id,
                req.getDayOfWeek(),
                req.getStartTime(),
                req.getEndTime(),
                req.getTeacherId(),
                req.getRoom(),
                req.getClassId());

        if (isConflict) {
            throw new RuntimeException(
                    "Scheduling conflict detected! The teacher, room, or class is already booked during this time.");
        }
    }

    private TimeTableResponse mapToResponse(TimeTable T) {
        TimeTableResponse ttrsp = new TimeTableResponse();
        ttrsp.setId(T.getId());

        if (T.getClazz() != null) {
            ttrsp.setClassId(T.getClazz().getId());
            ttrsp.setClassName(T.getClazz().getName());
        }

        if (T.getCourse() != null) {
            ttrsp.setCourseId(T.getCourse().getId());
            ttrsp.setCourseName(T.getCourse().getName());
        }

        ttrsp.setDayOfWeek(T.getDayOfWeek());
        ttrsp.setStartTime(T.getStartTime());
        ttrsp.setEndTime(T.getEndTime());
        ttrsp.setRoom(T.getRoom());

        if (T.getTeacher() != null) {
            ttrsp.setTeacherId(T.getTeacher().getId());
            ttrsp.setTeacherName(T.getTeacher().getFirstName() + " " + T.getTeacher().getLastName());
        }

        return ttrsp;
    }
}