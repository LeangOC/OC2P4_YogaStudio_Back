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

    @Test
    void shouldMapDtoToSessionWithNullTeacher() {

        SessionDto dto = new SessionDto();

        dto.setId(1L);
        dto.setName("Yoga Session");
        dto.setDescription("Description");
        dto.setTeacher_id(null);
        dto.setUsers(new ArrayList<>());

        Session session = sessionMapper.toEntity(dto);

        assertNotNull(session);

        assertNull(session.getTeacher());
    }

    @Test
    void shouldMapDtoToSessionWithNullUsers() {

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
        dto.setDescription("Description");
        dto.setTeacher_id(1L);

        dto.setUsers(null);

        Session session = sessionMapper.toEntity(dto);

        assertNotNull(session);

        assertNotNull(session.getUsers());

        assertTrue(session.getUsers().isEmpty());
    }

    @Test
    void shouldMapSessionToDtoWithNullUsers() {

        Teacher teacher = Teacher.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        Session session = Session.builder()
                .id(1L)
                .name("Yoga")
                .description("Description")
                .teacher(teacher)
                .users(null)
                .build();

        SessionDto dto = sessionMapper.toDto(session);

        assertNotNull(dto);

        assertNotNull(dto.getUsers());

        assertTrue(dto.getUsers().isEmpty());
    }

    @Test
    void shouldMapSessionToDtoWithNullTeacher() {

        Session session = Session.builder()
                .id(1L)
                .name("Yoga")
                .description("Description")
                .teacher(null)
                .users(new ArrayList<>())
                .build();

        SessionDto dto = sessionMapper.toDto(session);

        assertNotNull(dto);

        assertNull(dto.getTeacher_id());
    }

    @Test
    void shouldMapDtoToSessionWithUserIds() {

        sessionMapper.userService = mock(
                com.openclassrooms.starterjwt.services.UserService.class
        );

        Teacher teacher = Teacher.builder()
                .id(1L)
                .build();

        when(teacherService.findById(1L))
                .thenReturn(teacher);

        com.openclassrooms.starterjwt.models.User user =
                new com.openclassrooms.starterjwt.models.User();

        user.setId(10L);

        when(sessionMapper.userService.findById(10L))
                .thenReturn(user);

        SessionDto dto = new SessionDto();

        dto.setId(1L);
        dto.setName("Yoga");
        dto.setDescription("Description");
        dto.setTeacher_id(1L);
        dto.setUsers(java.util.List.of(10L));

        Session session = sessionMapper.toEntity(dto);

        assertNotNull(session);

        assertEquals(1, session.getUsers().size());

        assertEquals(10L, session.getUsers().get(0).getId());
    }

    @Test
    void shouldMapDtoToSessionWithNullUserReturnedByService() {

        sessionMapper.userService = mock(
                com.openclassrooms.starterjwt.services.UserService.class
        );

        Teacher teacher = Teacher.builder()
                .id(1L)
                .build();

        when(teacherService.findById(1L))
                .thenReturn(teacher);

        when(sessionMapper.userService.findById(99L))
                .thenReturn(null);

        SessionDto dto = new SessionDto();

        dto.setId(1L);
        dto.setName("Yoga");
        dto.setDescription("Description");
        dto.setTeacher_id(1L);
        dto.setUsers(java.util.List.of(99L));

        Session session = sessionMapper.toEntity(dto);

        assertNotNull(session);

        assertEquals(1, session.getUsers().size());

        assertNull(session.getUsers().get(0));
    }
}