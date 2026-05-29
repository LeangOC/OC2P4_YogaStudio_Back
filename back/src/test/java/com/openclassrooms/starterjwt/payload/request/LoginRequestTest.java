package com.openclassrooms.starterjwt.payload.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void shouldTestSettersAndGetters() {

        LoginRequest request =
                new LoginRequest();

        request.setEmail("test@test.com");
        request.setPassword("password");

        assertEquals(
                "test@test.com",
                request.getEmail()
        );

        assertEquals(
                "password",
                request.getPassword()
        );
    }

    @Test
    void shouldTestEqualsAndHashCode() {

        LoginRequest request1 =
                new LoginRequest();

        request1.setEmail("test@test.com");
        request1.setPassword("password");

        LoginRequest request2 =
                new LoginRequest();

        request2.setEmail("test@test.com");
        request2.setPassword("password");

        assertEquals(request1, request2);

        assertEquals(
                request1.hashCode(),
                request2.hashCode()
        );
    }

    @Test
    void shouldTestToString() {

        LoginRequest request =
                new LoginRequest();

        request.setEmail("test@test.com");
        request.setPassword("password");

        String result =
                request.toString();

        assertNotNull(result);

        assertTrue(
                result.contains("test@test.com")
        );
    }

    @Test
    void shouldNotBeEqual() {

        LoginRequest request1 =
                new LoginRequest();

        request1.setEmail("test1@test.com");
        request1.setPassword("password1");

        LoginRequest request2 =
                new LoginRequest();

        request2.setEmail("test2@test.com");
        request2.setPassword("password2");

        assertNotEquals(request1, request2);
    }
}