package com.openclassrooms.starterjwt.exception;

import com.openclassrooms.starterjwt.payload.response.ErrorResponse;

import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler =
            new GlobalExceptionHandler();

    @Test
    void shouldHandleNumberFormatException() {

        NumberFormatException exception =
                new NumberFormatException(
                        "Invalid number"
                );

        ResponseEntity<ErrorResponse> response =
                handler.handleNumberFormatException(
                        exception
                );

        assertNotNull(response);

        assertEquals(
                400,
                response.getStatusCode().value()
        );

        assertNotNull(
                response.getBody()
        );

        assertEquals(
                "BAD_REQUEST",
                response.getBody().getError()
        );

        assertEquals(
                "Invalid id format",
                response.getBody().getMessage()
        );
    }

    @Test
    void shouldHandleNotFoundException() {

        NotFoundException exception =
                new NotFoundException();

        ResponseEntity<ErrorResponse> response =
                handler.handleNotFoundException(
                        exception
                );

        assertNotNull(response);

        assertEquals(
                404,
                response.getStatusCode().value()
        );

        assertNotNull(
                response.getBody()
        );

        assertEquals(
                "NOT_FOUND",
                response.getBody().getError()
        );

        assertEquals(
                "Resource not found",
                response.getBody().getMessage()
        );
    }

    @Test
    void shouldHandleBadRequestException() {

        BadRequestException exception =
                new BadRequestException();

        ResponseEntity<ErrorResponse> response =
                handler.handleBadRequestException(
                        exception
                );

        assertNotNull(response);

        assertEquals(
                400,
                response.getStatusCode().value()
        );

        assertNotNull(
                response.getBody()
        );

        assertEquals(
                "BAD_REQUEST",
                response.getBody().getError()
        );

        assertEquals(
                "Bad request",
                response.getBody().getMessage()
        );
    }

    @Test
    void shouldHandleUnauthorizedException() {

        UnauthorizedException exception =
                new UnauthorizedException(
                        "Unauthorized access"
                );

        ResponseEntity<ErrorResponse> response =
                handler.handleUnauthorizedException(
                        exception
                );

        assertNotNull(response);

        assertEquals(
                401,
                response.getStatusCode().value()
        );

        assertNotNull(
                response.getBody()
        );

        assertEquals(
                "UNAUTHORIZED",
                response.getBody().getError()
        );

        assertEquals(
                "Unauthorized access",
                response.getBody().getMessage()
        );
    }

    @Test
    void shouldHandleValidationException() {

        BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(
                        new Object(),
                        "objectName"
                );

        bindingResult.addError(
                new FieldError(
                        "objectName",
                        "email",
                        "Email is invalid"
                )
        );

        MethodArgumentNotValidException exception =
                new MethodArgumentNotValidException(
                        null,
                        bindingResult
                );

        ResponseEntity<ErrorResponse> response =
                handler.handleValidationException(
                        exception
                );

        assertNotNull(response);

        assertEquals(
                400,
                response.getStatusCode().value()
        );

        assertNotNull(
                response.getBody()
        );

        assertEquals(
                "VALIDATION_ERROR",
                response.getBody().getError()
        );

        assertEquals(
                "Email is invalid",
                response.getBody().getMessage()
        );
    }
}