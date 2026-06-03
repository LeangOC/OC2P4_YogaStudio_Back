package com.openclassrooms.starterjwt.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.starterjwt.dto.SessionDto;
import com.openclassrooms.starterjwt.mapper.SessionMapper;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.services.SessionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SessionControllerTest {

    private MockMvc mockMvc;

    private SessionService sessionService;

    private SessionMapper sessionMapper;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {

        sessionService =
                Mockito.mock(SessionService.class);

        sessionMapper =
                Mockito.mock(SessionMapper.class);

        SessionController controller =
                new SessionController(
                        sessionService,
                        sessionMapper
                );

        mockMvc =
                MockMvcBuilders
                        .standaloneSetup(controller)
                        .build();

        objectMapper =
                new ObjectMapper();
    }

    @Test
    void shouldFindById() throws Exception {

        Session session =
                Session.builder()
                        .id(1L)
                        .name("Yoga")
                        .description("Description")
                        .date(new Date())
                        .build();

        SessionDto dto =
                new SessionDto();

        dto.setId(1L);

        dto.setName("Yoga");

        dto.setDescription("Description");

        when(sessionService.getById(1L))
                .thenReturn(session);

        when(sessionMapper.toDto(session))
                .thenReturn(dto);

        mockMvc.perform(get("/api/session/1"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.name")
                                .value("Yoga")
                );
    }

    @Test
    void shouldFindAll() throws Exception {

        List<Session> sessions =
                new ArrayList<>();

        Session session =
                Session.builder()
                        .id(1L)
                        .name("Yoga")
                        .description("Description")
                        .date(new Date())
                        .build();

        sessions.add(session);

        List<SessionDto> dtos =
                new ArrayList<>();

        SessionDto dto =
                new SessionDto();

        dto.setId(1L);

        dto.setName("Yoga");

        dto.setDescription("Description");

        dtos.add(dto);

        when(sessionService.findAll())
                .thenReturn(sessions);

        when(sessionMapper.toDto(sessions))
                .thenReturn(dtos);

        mockMvc.perform(get("/api/session"))
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$[0].name")
                                .value("Yoga")
                );
    }

    @Test
    void shouldCreateSession() throws Exception {

        SessionDto dto =
                new SessionDto();

        dto.setName("Yoga");

        dto.setDescription("Description");

        dto.setDate(new Date());

        dto.setTeacher_id(1L);

        dto.setUsers(new ArrayList<>());

        Session session =
                Session.builder()
                        .id(1L)
                        .name("Yoga")
                        .description("Description")
                        .date(new Date())
                        .build();

        when(sessionMapper.toEntity(any(SessionDto.class)))
                .thenReturn(session);

        when(sessionService.create(any(Session.class)))
                .thenReturn(session);

        when(sessionMapper.toDto(session))
                .thenReturn(dto);

        mockMvc.perform(
                        post("/api/session")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(
                                        objectMapper.writeValueAsString(dto)
                                )
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteSession() throws Exception {

        doNothing()
                .when(sessionService)
                .delete(1L);

        mockMvc.perform(delete("/api/session/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldParticipate() throws Exception {

        doNothing()
                .when(sessionService)
                .participate(1L, 1L);

        mockMvc.perform(
                        post("/api/session/1/participate/1")
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldNoLongerParticipate() throws Exception {

        doNothing()
                .when(sessionService)
                .noLongerParticipate(1L, 1L);

        mockMvc.perform(
                        delete("/api/session/1/participate/1")
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenUpdateIdIsInvalid()
            throws Exception {

        SessionDto dto =
                new SessionDto();

        dto.setName("Yoga");

        mockMvc.perform(
                        put("/api/session/abc")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(
                                        objectMapper.writeValueAsString(dto)
                                )
                )
                .andExpect(status().isBadRequest());
    }
}