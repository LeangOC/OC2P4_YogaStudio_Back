package com.openclassrooms.starterjwt.payload.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtResponseTestAdvanced {

    @Test
    void shouldCreateJwtResponseWithConstructor() {

        JwtResponse response =
                new JwtResponse(
                        "jwt-token",
                        1L,
                        "test@test.com",
                        "John",
                        "Doe",
                        false
                );

        assertEquals(
                "jwt-token",
                response.getToken()
        );

        assertEquals(
                "Bearer",
                response.getType()
        );

        assertEquals(
                1L,
                response.getId()
        );

        assertEquals(
                "test@test.com",
                response.getUsername()
        );

        assertEquals(
                "John",
                response.getFirstName()
        );

        assertEquals(
                "Doe",
                response.getLastName()
        );

        assertFalse(
                response.getAdmin()
        );
    }

    @Test
    void shouldSetAndGetAllFields() {

        JwtResponse response =
                new JwtResponse(
                        "token",
                        1L,
                        "mail@test.com",
                        "John",
                        "Doe",
                        true
                );

        response.setToken("new-token");
        response.setType("Custom");
        response.setId(99L);
        response.setUsername("new@test.com");
        response.setFirstName("Jane");
        response.setLastName("Smith");
        response.setAdmin(false);

        assertEquals(
                "new-token",
                response.getToken()
        );

        assertEquals(
                "Custom",
                response.getType()
        );

        assertEquals(
                99L,
                response.getId()
        );

        assertEquals(
                "new@test.com",
                response.getUsername()
        );

        assertEquals(
                "Jane",
                response.getFirstName()
        );

        assertEquals(
                "Smith",
                response.getLastName()
        );

        assertFalse(
                response.getAdmin()
        );
    }

    @Test
    void shouldSupportNullValues() {

        JwtResponse response =
                new JwtResponse(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );

        assertNull(
                response.getToken()
        );

        assertEquals(
                "Bearer",
                response.getType()
        );

        assertNull(
                response.getId()
        );

        assertNull(
                response.getUsername()
        );

        assertNull(
                response.getFirstName()
        );

        assertNull(
                response.getLastName()
        );

        assertNull(
                response.getAdmin()
        );
    }
}