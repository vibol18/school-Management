package com.example.school.repository;

import java.time.LocalTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.school.entity.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    @Query("SELECT COUNT(t) > 0 FROM TimeTable t WHERE t.dayOfWeek = :dayOfWeek " +
            "AND t.startTime < :endTime AND t.endTime > :startTime " +
            "AND (:id IS NULL OR t.id != :id) " +
            "AND (t.teacher.id = :teacherId OR t.room = :room OR t.clazz.id = :classId)")
    boolean hasScheduleConflict(
            @Param("id") Long id,
            @Param("dayOfWeek") String dayOfWeek,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime,
            @Param("teacherId") Long teacherId,
            @Param("room") String room,
            @Param("classId") Long classId);
}