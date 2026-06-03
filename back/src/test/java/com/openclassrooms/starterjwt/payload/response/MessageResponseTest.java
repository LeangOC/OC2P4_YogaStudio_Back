package com.openclassrooms.starterjwt.payload.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageResponseTest {

    @Test
    void shouldTestConstructorAndGetter() {

        MessageResponse response =
                new MessageResponse(
                        "Message de test"
                );

        assertNotNull(response);

        assertEquals(
                "Message de test",
                response.getMessage()
        );
    }

    @Test
    void shouldTestSetter() {

        MessageResponse response =
                new MessageResponse(
                        "Initial message"
                );

        response.setMessage(
                "Updated message"
        );

        assertEquals(
                "Updated message",
                response.getMessage()
        );
    }

    @Test
    void shouldTestEqualsAndHashCode() {

        MessageResponse response1 =
                new MessageResponse(
                        "Test message"
                );

        MessageResponse response2 =
                new MessageResponse(
                        "Test message"
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

        MessageResponse response =
                new MessageResponse(
                        "Hello world"
                );

        String result =
                response.toString();

        assertNotNull(result);

        assertTrue(
                result.contains("Hello world")
        );
    }

    @Test
    void shouldNotBeEqual() {

        MessageResponse response1 =
                new MessageResponse(
                        "Message 1"
                );

        MessageResponse response2 =
                new MessageResponse(
                        "Message 2"
                );

        assertNotEquals(
                response1,
                response2
        );
    }
}