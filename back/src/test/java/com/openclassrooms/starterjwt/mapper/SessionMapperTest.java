package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.services.TeacherService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SessionMapperTest {

    private SessionMapperImpl sessionMapper;

    private TeacherService teacherService;

    @BeforeEach
    void setup() {

        teacherService = mock(TeacherService.class);

        sessionMapper = new SessionMapperImpl();

        sessionMapper.teacherService = teacherService;
    }

    @Test
    void shouldMapSessionToDto() {

        Teacher teacher = Teacher.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        Session session = Session.builder()
                .id(1L)
                .name("Yoga Session")
                .description("Yoga description")
                .teacher(teacher)
                .users(new ArrayList<>())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        SessionDto dto = sessionMapper.toDto(session);

        assertNotNull(dto);

        assertEquals(session.getId(), dto.getId());
        assertEquals(session.getName(), dto.getName());
        assertEquals(session.getDescription(), dto.getDescription());
        assertEquals(session.getTeacher().getId(), dto.getTeacher_id());
    }

    @Test
    void shouldMapDtoToSession() {

        Teacher teacher = Teacher.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        when(teacherService.findById(1L))
                .thenReturn(teacher);

        SessionDto dto = new SessionDto();

        dto.setId(1L);
        dto.setName("Yoga Session");
        dto.setDescription("Yoga description");
        dto.setTeacher_id(1L);
        dto.setUsers(new ArrayList<>());
        dto.setCreatedAt(LocalDateTime.now());
        dto.setUpdatedAt(LocalDateTime.now());

        Session session = sessionMapper.toEntity(dto);

        assertNotNull(session);

        assertEquals(dto.getId(), session.getId());
        assertEquals(dto.getName(), session.getName());
        assertEquals(dto.getDescription(), session.getDescription());
        assertEquals(dto.getTeacher_id(), session.getTeacher().getId());
    }
}