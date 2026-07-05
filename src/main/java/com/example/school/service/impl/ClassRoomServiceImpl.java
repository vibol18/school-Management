package com.example.school.service.impl;

import com.example.school.entity.ClassRoom;
import com.example.school.repository.ClassRoomrepository;
import com.example.school.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    private ClassRoomrepository repo;

    @Override
    public List<ClassRoom> getAll() {
        return repo.findAll();
    }

    @Override
    public ClassRoom getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Class not found"));
    }

    @Override
    public ClassRoom create(ClassRoom classroom) {
        return repo.save(classroom);
    }
    @Override
    public ClassRoom update(Long id, ClassRoom classroom) {
        ClassRoom existing = getById(id);

        existing.setName(classroom.getName());
        existing.setRoomNumber(classroom.getRoomNumber());
        existing.setSection(classroom.getSection());
        existing.setTeacher(classroom.getTeacher());
        existing.setSubjects(classroom.getSubjects());
        existing.setTeachers(classroom.getTeachers());

        return repo.save(existing);
    }
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}