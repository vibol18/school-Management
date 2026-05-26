package com.example.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.entity.TimeTable;

public interface TimeTableRepository
                extends JpaRepository<TimeTable, Long> {

}
