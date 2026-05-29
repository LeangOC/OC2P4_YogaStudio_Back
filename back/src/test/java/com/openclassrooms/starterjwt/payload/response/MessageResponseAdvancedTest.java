package com.openclassrooms.starterjwt.payload.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageResponseAdvancedTest {

    @Test
    void shouldCreateMessageResponseWithConstructor() {

        MessageResponse response =
                new MessageResponse(
                        "Operation successful"
                );

        assertEquals(
                "Operation successful",
                response.getMessage()
        );
    }

    @Test
    void shouldSetAndGetMessage() {

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
    void shouldSupportNullMessage() {

        MessageResponse response =
                new MessageResponse(
                        null
                );

        assertNull(
                response.getMessage()
        );
    }

    @Test
    void shouldSupportEmptyMessage() {

        MessageResponse response =
                new MessageResponse(
                        ""
                );

        assertEquals(
                "",
                response.getMessage()
        );
    }

    @Test
    void shouldSupportLongMessage() {

        String longMessage =
                "This is a very long message used for testing purposes "
                        + "to improve JaCoCo coverage in payload response package.";

        MessageResponse response =
                new MessageResponse(
                        longMessage
                );

        assertEquals(
                longMessage,
                response.getMessage()
        );
    }
}