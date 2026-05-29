package com.openclassrooms.starterjwt.security.jwt;

import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilsTest {

    private JwtUtils jwtUtils;

    @BeforeEach
    void setup() {

        jwtUtils = new JwtUtils();

        jwtUtils.setJwtSecret("openclassroomsSecretKeyopenclassroomsSecretKeyopenclassroomsSecretKeyopenclassroomsSecretKey");
        jwtUtils.setJwtExpirationMs(86400000);
    }

    @Test
    void shouldGenerateJwtToken() {

        UserDetailsImpl userDetails = new UserDetailsImpl(
                1L,
                "test@test.com",
                "John",
                "Doe",
                false,
                "password"
        );

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        new ArrayList<>()
                );

        String token = jwtUtils.generateJwtToken(authentication);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void shouldGetUserNameFromJwtToken() {

        UserDetailsImpl userDetails = new UserDetailsImpl(
                1L,
                "test@test.com",
                "John",
                "Doe",
                false,
                "password"
        );

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        new ArrayList<>()
                );

        String token = jwtUtils.generateJwtToken(authentication);

        String username = jwtUtils.getUserNameFromJwtToken(token);

        assertEquals("test@test.com", username);
    }

    @Test
    void shouldValidateJwtToken() {

        UserDetailsImpl userDetails = new UserDetailsImpl(
                1L,
                "test@test.com",
                "John",
                "Doe",
                false,
                "password"
        );

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        new ArrayList<>()
                );

        String token = jwtUtils.generateJwtToken(authentication);

        boolean isValid = jwtUtils.validateJwtToken(token);

        assertTrue(isValid);
    }

    @Test
    void shouldReturnFalseForInvalidJwtToken() {

        boolean isValid =
                jwtUtils.validateJwtToken("invalid-token");

        assertFalse(isValid);
    }
}