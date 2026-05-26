package com.example.school.service;

import java.util.List;

import com.example.school.dto.TimeTableReq;
import com.example.school.dto.TimeTableResponse;

public interface TimeTableService {

    TimeTableResponse create(TimeTableReq request);

    List<TimeTableResponse> getAll();

    TimeTableResponse getById(Long id);

    TimeTableResponse update(Long id, TimeTableReq request);

    void delete(Long id);
}