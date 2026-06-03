package com.openclassrooms.starterjwt.payload.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtResponseTest {

    @Test
    void shouldTestConstructorAndGetters() {

        JwtResponse response =
                new JwtResponse(
                        "token",
                        1L,
                        "test@test.com",
                        "John",
                        "Doe",
                        false
                );

        assertNotNull(response);

        assertEquals(
                "token",
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
    void shouldTestSetters() {

        JwtResponse response =
                new JwtResponse(
                        "token",
                        1L,
                        "test@test.com",
                        "John",
                        "Doe",
                        false
                );

        response.setToken("newToken");

        response.setType("Bearer");

        response.setId(2L);

        response.setUsername("new@test.com");

        response.setFirstName("Jane");

        response.setLastName("Smith");

        response.setAdmin(true);

        assertEquals(
                "newToken",
                response.getToken()
        );

        assertEquals(
                "Bearer",
                response.getType()
        );

        assertEquals(
                2L,
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

        assertTrue(
                response.getAdmin()
        );
    }
}