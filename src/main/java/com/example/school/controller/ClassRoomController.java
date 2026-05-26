package com.example.school.controller;

import com.example.school.entity.ClassRoom;
import com.example.school.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "http://localhost:5173")
public class ClassRoomController {

    @Autowired
    private ClassRoomService service;

    // GET ALL
    @GetMapping
    public List<ClassRoom> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ClassRoom getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // CREATE
    @PostMapping
    public ClassRoom create(@RequestBody ClassRoom classroom) {
        return service.create(classroom);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ClassRoom update(@PathVariable Long id, @RequestBody ClassRoom classroom) {
        return service.update(id, classroom);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}