package com.example.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.school.entity.ClassRoom;

public interface ClassRoomrepository extends JpaRepository<ClassRoom, Long> {
    @EntityGraph(attributePaths = { "exams" })
    List<ClassRoom> findAll();
}
