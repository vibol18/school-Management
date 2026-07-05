package com.example.school.service.impl;

import com.example.school.dto.SchoolReq;
import com.example.school.dto.SchoolResponse;
import com.example.school.entity.School;
import com.example.school.repository.SchoolRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SchoolServiceImplTest {

    @Mock
    private SchoolRepository schoolRepository;

    @InjectMocks
    private SchoolServiceImpl schoolService;

    @Test
    void shouldCreateSchool() {
        SchoolReq request = new SchoolReq();
        request.setName("Green Valley School");
        request.setAddress("123 Main Street");

        School savedSchool = new School();
        savedSchool.setId(1L);
        savedSchool.setName("Green Valley School");
        savedSchool.setAddress("123 Main Street");

        when(schoolRepository.existsByName("Green Valley School")).thenReturn(false);
        when(schoolRepository.save(savedSchool)).thenReturn(savedSchool);

        SchoolResponse response = schoolService.createSchool(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Green Valley School", response.getName());
        assertEquals("123 Main Street", response.getAddress());
    }

    @Test
    void shouldReturnSchoolById() {
        School school = new School();
        school.setId(2L);
        school.setName("Bright Future School");
        school.setAddress("456 Oak Avenue");

        when(schoolRepository.findById(2L)).thenReturn(java.util.Optional.of(school));

        SchoolResponse response = schoolService.getSchoolById(2L);

        assertNotNull(response);
        assertEquals(2L, response.getId());
        assertEquals("Bright Future School", response.getName());
        assertEquals("456 Oak Avenue", response.getAddress());
    }
}
