package com.openclassrooms.starterjwt.security;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRejectUnauthorizedUser()
            throws Exception {

        mockMvc.perform(
                        get("/api/session")
                )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void shouldAllowAuthenticatedUser()
            throws Exception {

        mockMvc.perform(
                        get("/api/session")
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldAllowRegisterEndpointWithoutAuthentication()
            throws Exception {

        mockMvc.perform(
                        get("/api/auth/register")
                )
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void shouldAllowLoginEndpointWithoutAuthentication()
            throws Exception {

        mockMvc.perform(
                        get("/api/auth/login")
                )
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    @WithMockUser
    void shouldAllowTeacherEndpointForAuthenticatedUser()
            throws Exception {

        mockMvc.perform(
                        get("/api/teacher")
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldRejectTeacherEndpointWithoutAuthentication()
            throws Exception {

        mockMvc.perform(
                        get("/api/teacher")
                )
                .andExpect(status().isUnauthorized());
    }
}