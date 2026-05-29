package com.openclassrooms.starterjwt.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.payload.request.LoginRequest;
import com.openclassrooms.starterjwt.payload.request.SignupRequest;
import com.openclassrooms.starterjwt.repository.UserRepository;
import com.openclassrooms.starterjwt.security.jwt.JwtUtils;
import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {

        userRepository.deleteAll();
    }

    @Test
    void shouldRegisterUser() throws Exception {

        SignupRequest signupRequest =
                new SignupRequest();

        signupRequest.setEmail("new@test.com");
        signupRequest.setFirstName("John");
        signupRequest.setLastName("Doe");
        signupRequest.setPassword("password");

        when(passwordEncoder.encode(any()))
                .thenReturn("encodedPassword");

        mockMvc.perform(
                        post("/api/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(
                                                signupRequest
                                        )
                                )
                )
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.message")
                                .value(
                                        "User registered successfully!"
                                )
                );

        verify(passwordEncoder)
                .encode("password");
    }

    @Test
    void shouldFailWhenEmailAlreadyExists()
            throws Exception {

        SignupRequest signupRequest =
                new SignupRequest();

        signupRequest.setEmail("duplicate@test.com");
        signupRequest.setFirstName("John");
        signupRequest.setLastName("Doe");
        signupRequest.setPassword("password");

        when(passwordEncoder.encode(any()))
                .thenReturn("encodedPassword");

        mockMvc.perform(
                post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(
                                        signupRequest
                                )
                        )
        );

        mockMvc.perform(
                        post("/api/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(
                                                signupRequest
                                        )
                                )
                )
                .andExpect(status().isBadRequest())
                .andExpect(
                        jsonPath("$.message")
                                .value(
                                        "Error: Email is already taken!"
                                )
                );
    }

    @Test
    void shouldAuthenticateUser()
            throws Exception {

        LoginRequest loginRequest =
                new LoginRequest();

        loginRequest.setEmail("test@test.com");
        loginRequest.setPassword("password");

        UserDetailsImpl userDetails =
                new UserDetailsImpl(
                        1L,
                        "test@test.com",
                        "John",
                        "Doe",
                        false,
                        "password"
                );

        Authentication authentication =
                mock(Authentication.class);

        when(authentication.getPrincipal())
                .thenReturn(userDetails);

        when(authenticationManager.authenticate(any(
                UsernamePasswordAuthenticationToken.class
        ))).thenReturn(authentication);

        when(jwtUtils.generateJwtToken(authentication))
                .thenReturn("jwt-token");

        User user =
                User.builder()
                        .email("test@test.com")
                        .firstName("John")
                        .lastName("Doe")
                        .password("password")
                        .admin(false)
                        .build();

        userRepository.save(user);

        mockMvc.perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(
                                                loginRequest
                                        )
                                )
                )
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.token")
                                .value("jwt-token")
                )
                .andExpect(
                        jsonPath("$.username")
                                .value("test@test.com")
                )
                .andExpect(
                        jsonPath("$.firstName")
                                .value("John")
                )
                .andExpect(
                        jsonPath("$.lastName")
                                .value("Doe")
                )
                .andExpect(
                        jsonPath("$.admin")
                                .value(false)
                );
    }

    @Test
    void shouldAuthenticateUserWhenUserIsAdmin()
            throws Exception {

        LoginRequest loginRequest =
                new LoginRequest();

        loginRequest.setEmail("admin@test.com");
        loginRequest.setPassword("password");

        UserDetailsImpl userDetails =
                new UserDetailsImpl(
                        2L,
                        "admin@test.com",
                        "Admin",
                        "User",
                        true,
                        "password"
                );

        Authentication authentication =
                mock(Authentication.class);

        when(authentication.getPrincipal())
                .thenReturn(userDetails);

        when(authenticationManager.authenticate(any(
                UsernamePasswordAuthenticationToken.class
        ))).thenReturn(authentication);

        when(jwtUtils.generateJwtToken(authentication))
                .thenReturn("admin-token");

        User admin =
                User.builder()
                        .email("admin@test.com")
                        .firstName("Admin")
                        .lastName("User")
                        .password("password")
                        .admin(true)
                        .build();

        userRepository.save(admin);

        mockMvc.perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(
                                                loginRequest
                                        )
                                )
                )
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$.admin")
                                .value(true)
                );
    }

    @Test
    void shouldFailWhenEmailIsInvalid()
            throws Exception {

        SignupRequest signupRequest =
                new SignupRequest();

        signupRequest.setEmail("invalid-email");
        signupRequest.setFirstName("John");
        signupRequest.setLastName("Doe");
        signupRequest.setPassword("password");

        mockMvc.perform(
                        post("/api/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(
                                                signupRequest
                                        )
                                )
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldFailWhenLoginPasswordIsBlank()
            throws Exception {

        LoginRequest loginRequest =
                new LoginRequest();

        loginRequest.setEmail("test@test.com");
        loginRequest.setPassword("");

        mockMvc.perform(
                        post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(
                                                loginRequest
                                        )
                                )
                )
                .andExpect(status().isBadRequest());
    }
}