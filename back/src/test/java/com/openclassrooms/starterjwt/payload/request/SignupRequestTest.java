package com.openclassrooms.starterjwt.payload.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignupRequestTest {

    @Test
    void shouldTestSettersAndGetters() {

        SignupRequest request =
                new SignupRequest();

        request.setEmail("test@test.com");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPassword("password");

        assertEquals(
                "test@test.com",
                request.getEmail()
        );

        assertEquals(
                "John",
                request.getFirstName()
        );

        assertEquals(
                "Doe",
                request.getLastName()
        );

        assertEquals(
                "password",
                request.getPassword()
        );
    }

    @Test
    void shouldTestEqualsAndHashCode() {

        SignupRequest request1 =
                new SignupRequest();

        request1.setEmail("test@test.com");
        request1.setFirstName("John");
        request1.setLastName("Doe");
        request1.setPassword("password");

        SignupRequest request2 =
                new SignupRequest();

        request2.setEmail("test@test.com");
        request2.setFirstName("John");
        request2.setLastName("Doe");
        request2.setPassword("password");

        assertEquals(request1, request2);

        assertEquals(
                request1.hashCode(),
                request2.hashCode()
        );
    }

    @Test
    void shouldTestToString() {

        SignupRequest request =
                new SignupRequest();

        request.setEmail("test@test.com");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPassword("password");

        String result =
                request.toString();

        assertNotNull(result);

        assertTrue(
                result.contains("test@test.com")
        );

        assertTrue(
                result.contains("John")
        );
    }

    @Test
    void shouldNotBeEqual() {

        SignupRequest request1 =
                new SignupRequest();

        request1.setEmail("test1@test.com");
        request1.setFirstName("John");
        request1.setLastName("Doe");
        request1.setPassword("password1");

        SignupRequest request2 =
                new SignupRequest();

        request2.setEmail("test2@test.com");
        request2.setFirstName("Jane");
        request2.setLastName("Smith");
        request2.setPassword("password2");

        assertNotEquals(request1, request2);
    }
}