package com.openclassrooms.starterjwt.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDtoTest {

    @Test
    void shouldTestSettersAndGetters() {

        LocalDateTime now = LocalDateTime.now();

        TeacherDto teacherDto = new TeacherDto();

        teacherDto.setId(1L);
        teacherDto.setFirstName("John");
        teacherDto.setLastName("Doe");
        teacherDto.setCreatedAt(now);
        teacherDto.setUpdatedAt(now);

        assertEquals(1L, teacherDto.getId());
        assertEquals("John", teacherDto.getFirstName());
        assertEquals("Doe", teacherDto.getLastName());

        assertEquals(now, teacherDto.getCreatedAt());
        assertEquals(now, teacherDto.getUpdatedAt());
    }

    @Test
    void shouldTestEqualsAndHashCode() {

        LocalDateTime now = LocalDateTime.now();

        TeacherDto teacher1 = new TeacherDto();

        teacher1.setId(1L);
        teacher1.setFirstName("John");
        teacher1.setLastName("Doe");
        teacher1.setCreatedAt(now);
        teacher1.setUpdatedAt(now);

        TeacherDto teacher2 = new TeacherDto();

        teacher2.setId(1L);
        teacher2.setFirstName("John");
        teacher2.setLastName("Doe");
        teacher2.setCreatedAt(now);
        teacher2.setUpdatedAt(now);

        assertEquals(teacher1, teacher2);

        assertEquals(teacher1.hashCode(), teacher2.hashCode());
    }

    @Test
    void shouldTestNotEquals() {

        TeacherDto teacher1 = new TeacherDto();
        teacher1.setId(1L);

        TeacherDto teacher2 = new TeacherDto();
        teacher2.setId(2L);

        assertNotEquals(teacher1, teacher2);
    }
}