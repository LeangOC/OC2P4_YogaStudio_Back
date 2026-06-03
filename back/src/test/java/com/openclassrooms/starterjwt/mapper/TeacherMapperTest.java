package com.openclassrooms.starterjwt.mapper;

import com.openclassrooms.starterjwt.dto.TeacherDto;
import com.openclassrooms.starterjwt.models.Teacher;

import org.junit.jupiter.api.Test;

import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherMapperTest {

    private final TeacherMapper mapper =
            Mappers.getMapper(TeacherMapper.class);

    @Test
    void shouldMapTeacherToDto() {

        LocalDateTime now =
                LocalDateTime.now();

        Teacher teacher =
                Teacher.builder()
                        .id(1L)
                        .firstName("John")
                        .lastName("Doe")
                        .createdAt(now)
                        .updatedAt(now)
                        .build();

        TeacherDto dto =
                mapper.toDto(teacher);

        assertNotNull(dto);

        assertEquals(
                teacher.getId(),
                dto.getId()
        );

        assertEquals(
                teacher.getFirstName(),
                dto.getFirstName()
        );

        assertEquals(
                teacher.getLastName(),
                dto.getLastName()
        );

        assertEquals(
                teacher.getCreatedAt(),
                dto.getCreatedAt()
        );

        assertEquals(
                teacher.getUpdatedAt(),
                dto.getUpdatedAt()
        );
    }

    @Test
    void shouldMapDtoToTeacher() {

        LocalDateTime now =
                LocalDateTime.now();

        TeacherDto dto =
                new TeacherDto();

        dto.setId(1L);
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setCreatedAt(now);
        dto.setUpdatedAt(now);

        Teacher teacher =
                mapper.toEntity(dto);

        assertNotNull(teacher);

        assertEquals(
                dto.getId(),
                teacher.getId()
        );

        assertEquals(
                dto.getFirstName(),
                teacher.getFirstName()
        );

        assertEquals(
                dto.getLastName(),
                teacher.getLastName()
        );

        assertEquals(
                dto.getCreatedAt(),
                teacher.getCreatedAt()
        );

        assertEquals(
                dto.getUpdatedAt(),
                teacher.getUpdatedAt()
        );
    }

    @Test
    void shouldMapTeacherListToDtoList() {

        Teacher teacher1 =
                Teacher.builder()
                        .id(1L)
                        .firstName("John")
                        .lastName("Doe")
                        .build();

        Teacher teacher2 =
                Teacher.builder()
                        .id(2L)
                        .firstName("Jane")
                        .lastName("Smith")
                        .build();

        List<Teacher> teachers =
                new ArrayList<>();

        teachers.add(teacher1);
        teachers.add(teacher2);

        List<TeacherDto> dtos =
                mapper.toDto(teachers);

        assertNotNull(dtos);

        assertEquals(
                2,
                dtos.size()
        );

        assertEquals(
                "John",
                dtos.get(0).getFirstName()
        );

        assertEquals(
                "Jane",
                dtos.get(1).getFirstName()
        );
    }

    @Test
    void shouldMapDtoListToTeacherList() {

        TeacherDto dto1 =
                new TeacherDto();

        dto1.setId(1L);
        dto1.setFirstName("John");
        dto1.setLastName("Doe");

        TeacherDto dto2 =
                new TeacherDto();

        dto2.setId(2L);
        dto2.setFirstName("Jane");
        dto2.setLastName("Smith");

        List<TeacherDto> dtos =
                new ArrayList<>();

        dtos.add(dto1);
        dtos.add(dto2);

        List<Teacher> teachers =
                mapper.toEntity(dtos);

        assertNotNull(teachers);

        assertEquals(
                2,
                teachers.size()
        );

        assertEquals(
                "John",
                teachers.get(0).getFirstName()
        );

        assertEquals(
                "Jane",
                teachers.get(1).getFirstName()
        );
    }

    @Test
    void shouldReturnNullWhenTeacherIsNull() {

        TeacherDto dto =
                mapper.toDto((Teacher) null);

        assertNull(dto);
    }

    @Test
    void shouldReturnNullWhenDtoIsNull() {

        Teacher teacher =
                mapper.toEntity((TeacherDto) null);

        assertNull(teacher);
    }

    @Test
    void shouldReturnEmptyListWhenTeacherListEmpty() {

        List<Teacher> teachers =
                new ArrayList<>();

        List<TeacherDto> dtos =
                mapper.toDto(teachers);

        assertNotNull(dtos);

        assertTrue(
                dtos.isEmpty()
        );
    }

    @Test
    void shouldReturnEmptyListWhenDtoListEmpty() {

        List<TeacherDto> dtos =
                new ArrayList<>();

        List<Teacher> teachers =
                mapper.toEntity(dtos);

        assertNotNull(teachers);

        assertTrue(
                teachers.isEmpty()
        );
    }
}