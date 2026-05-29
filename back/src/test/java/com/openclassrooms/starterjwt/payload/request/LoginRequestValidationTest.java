package com.openclassrooms.starterjwt.payload.request;

import jakarta.validation.ConstraintViolation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestValidationTest {

    private Validator validator;

    @BeforeEach
    void setup() {

        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();

        validator = factory.getValidator();
    }

    @Test
    void shouldValidateCorrectLoginRequest() {

        LoginRequest request =
                new LoginRequest();

        request.setEmail("test@test.com");
        request.setPassword("password123");

        Set<ConstraintViolation<LoginRequest>> violations =
                validator.validate(request);

        assertTrue(
                violations.isEmpty()
        );
    }

    @Test
    void shouldFailWhenEmailIsBlank() {

        LoginRequest request =
                new LoginRequest();

        request.setEmail("");
        request.setPassword("password123");

        Set<ConstraintViolation<LoginRequest>> violations =
                validator.validate(request);

        assertFalse(
                violations.isEmpty()
        );
    }

    @Test
    void shouldFailWhenPasswordIsBlank() {

        LoginRequest request =
                new LoginRequest();

        request.setEmail("test@test.com");
        request.setPassword("");

        Set<ConstraintViolation<LoginRequest>> violations =
                validator.validate(request);

        assertFalse(
                violations.isEmpty()
        );
    }

    @Test
    void shouldFailWhenFieldsAreNull() {

        LoginRequest request =
                new LoginRequest();

        Set<ConstraintViolation<LoginRequest>> violations =
                validator.validate(request);

        assertEquals(
                2,
                violations.size()
        );
    }
}