package com.openclassrooms.starterjwt.controllers;

import com.openclassrooms.starterjwt.models.Teacher;
import com.openclassrooms.starterjwt.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TeacherRepository teacherRepository;

    private Long teacherId;

    @BeforeEach
    void setup() {

        teacherRepository.deleteAll();

        Teacher teacher = Teacher.builder()
                .firstName("John")
                .lastName("Doe")
                .build();

        teacherId = teacherRepository.save(teacher).getId();
    }

    @Test
    @WithMockUser
    void shouldFindAllTeachers() throws Exception {

        mockMvc.perform(get("/api/teacher"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    @WithMockUser
    void shouldFindTeacherById() throws Exception {

        mockMvc.perform(get("/api/teacher/" + teacherId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }
}