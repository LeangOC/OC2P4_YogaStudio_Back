package com.openclassrooms.starterjwt.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void shouldTestSettersAndGetters() {

        LocalDateTime now = LocalDateTime.now();

        Teacher teacher = new Teacher();

        teacher.setId(1L);

        teacher.setFirstName("John");

        teacher.setLastName("Doe");

        teacher.setCreatedAt(now);

        teacher.setUpdatedAt(now);

        assertEquals(1L, teacher.getId());

        assertEquals("John", teacher.getFirstName());

        assertEquals("Doe", teacher.getLastName());

        assertEquals(now, teacher.getCreatedAt());

        assertEquals(now, teacher.getUpdatedAt());
    }

    @Test
    void shouldTestEqualsAndHashCode() {

        LocalDateTime now = LocalDateTime.now();

        Teacher teacher1 = new Teacher();

        teacher1.setId(1L);

        teacher1.setFirstName("John");

        teacher1.setLastName("Doe");

        teacher1.setCreatedAt(now);

        teacher1.setUpdatedAt(now);

        Teacher teacher2 = new Teacher();

        teacher2.setId(1L);

        teacher2.setFirstName("John");

        teacher2.setLastName("Doe");

        teacher2.setCreatedAt(now);

        teacher2.setUpdatedAt(now);

        assertEquals(teacher1, teacher2);

        assertEquals(
                teacher1.hashCode(),
                teacher2.hashCode()
        );
    }

    @Test
    void shouldTestNotEquals() {

        Teacher teacher1 = new Teacher();

        teacher1.setId(1L);

        Teacher teacher2 = new Teacher();

        teacher2.setId(2L);

        assertNotEquals(teacher1, teacher2);
    }

    @Test
    void shouldTestBuilder() {

        LocalDateTime now = LocalDateTime.now();

        Teacher teacher = Teacher.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .createdAt(now)
                .updatedAt(now)
                .build();

        assertNotNull(teacher);

        assertEquals(1L, teacher.getId());

        assertEquals("John", teacher.getFirstName());

        assertEquals("Doe", teacher.getLastName());

        assertEquals(now, teacher.getCreatedAt());

        assertEquals(now, teacher.getUpdatedAt());
    }

    @Test
    void shouldTestEqualsWithSameObject() {

        Teacher teacher = new Teacher();

        teacher.setId(1L);

        assertEquals(teacher, teacher);
    }

    @Test
    void shouldTestEqualsWithNull() {

        Teacher teacher = new Teacher();

        teacher.setId(1L);

        assertNotEquals(null, teacher);
    }

    @Test
    void shouldTestEqualsWithDifferentClass() {

        Teacher teacher = new Teacher();

        teacher.setId(1L);

        assertNotEquals(teacher, "teacher");
    }

    @Test
    void shouldTestEqualsWithNullIds() {

        Teacher teacher1 = new Teacher();

        Teacher teacher2 = new Teacher();

        assertEquals(teacher1, teacher2);
    }

    @Test
    void shouldTestEqualsWithOneNullId() {

        Teacher teacher1 = new Teacher();

        teacher1.setId(1L);

        Teacher teacher2 = new Teacher();

        assertNotEquals(teacher1, teacher2);
    }

    @Test
    void shouldTestHashCodeWithNullId() {

        Teacher teacher = new Teacher();

        int hashCode = teacher.hashCode();

        assertNotEquals(0, hashCode);
    }

    @Test
    void shouldTestHashCodeWithId() {

        Teacher teacher = new Teacher();

        teacher.setId(1L);

        int hashCode = teacher.hashCode();

        assertNotEquals(0, hashCode);
    }

    @Test
    void shouldTestEqualsWithDifferentFieldsButSameId() {

        Teacher teacher1 = new Teacher();

        teacher1.setId(1L);
        teacher1.setFirstName("John");
        teacher1.setLastName("Doe");

        Teacher teacher2 = new Teacher();

        teacher2.setId(1L);
        teacher2.setFirstName("Jane");
        teacher2.setLastName("Smith");

        assertEquals(teacher1, teacher2);
    }
}