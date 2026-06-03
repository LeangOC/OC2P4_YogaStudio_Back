package com.openclassrooms.starterjwt.payload.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void shouldTestConstructorAndGetters() {

        LocalDateTime now =
                LocalDateTime.now();

        ErrorResponse response =
                new ErrorResponse(
                        404,
                        "NOT_FOUND",
                        "Resource not found",
                        now
                );

        assertNotNull(response);

        assertEquals(
                404,
                response.getStatus()
        );

        assertEquals(
                "NOT_FOUND",
                response.getError()
        );

        assertEquals(
                "Resource not found",
                response.getMessage()
        );

        assertEquals(
                now,
                response.getTimestamp()
        );
    }

    @Test
    void shouldTestSetters() {

        ErrorResponse response =
                new ErrorResponse(
                        400,
                        "BAD_REQUEST",
                        "Initial message",
                        LocalDateTime.now()
                );

        LocalDateTime newTime =
                LocalDateTime.now();

        response.setStatus(500);
        response.setError("SERVER_ERROR");
        response.setMessage("Updated message");
        response.setTimestamp(newTime);

        assertEquals(
                500,
                response.getStatus()
        );

        assertEquals(
                "SERVER_ERROR",
                response.getError()
        );

        assertEquals(
                "Updated message",
                response.getMessage()
        );

        assertEquals(
                newTime,
                response.getTimestamp()
        );
    }

    @Test
    void shouldTestEqualsAndHashCode() {

        LocalDateTime now =
                LocalDateTime.now();

        ErrorResponse response1 =
                new ErrorResponse(
                        400,
                        "BAD_REQUEST",
                        "Error",
                        now
                );

        ErrorResponse response2 =
                new ErrorResponse(
                        400,
                        "BAD_REQUEST",
                        "Error",
                        now
                );

        assertEquals(
                response1,
                response2
        );

        assertEquals(
                response1.hashCode(),
                response2.hashCode()
        );
    }

    @Test
    void shouldTestToString() {

        ErrorResponse response =
                new ErrorResponse(
                        500,
                        "SERVER_ERROR",
                        "Internal error",
                        LocalDateTime.now()
                );

        String result =
                response.toString();

        assertNotNull(result);

        assertTrue(
                result.contains("SERVER_ERROR")
        );

        assertTrue(
                result.contains("Internal error")
        );
    }

    @Test
    void shouldNotBeEqual() {

        ErrorResponse response1 =
                new ErrorResponse(
                        400,
                        "BAD_REQUEST",
                        "Error 1",
                        LocalDateTime.now()
                );

        ErrorResponse response2 =
                new ErrorResponse(
                        500,
                        "SERVER_ERROR",
                        "Error 2",
                        LocalDateTime.now()
                );

        assertNotEquals(
                response1,
                response2
        );
    }
}