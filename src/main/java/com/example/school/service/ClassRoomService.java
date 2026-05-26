package com.example.school.service;

import com.example.school.entity.ClassRoom;
import java.util.List;

public interface ClassRoomService {
    List<ClassRoom> getAll();

    ClassRoom getById(Long id);

    ClassRoom create(ClassRoom classroom);

    ClassRoom update(Long id, ClassRoom classroom);

    void delete(Long id);
}