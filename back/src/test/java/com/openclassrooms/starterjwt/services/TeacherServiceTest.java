package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @Test
    void shouldFindAllTeachers() {
        when(teacherRepository.findAll())
                .thenReturn(List.of(new Teacher()));

        List<Teacher> result = teacherService.findAll();

        assertEquals(1, result.size());
    }

    @Test
    void shouldFindTeacherById() {
        Teacher teacher = Teacher.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        when(teacherRepository.findById(1L))
                .thenReturn(Optional.of(teacher));

        Teacher result = teacherService.findById(1L);

        assertEquals("John", result.getFirstName());
    }
}