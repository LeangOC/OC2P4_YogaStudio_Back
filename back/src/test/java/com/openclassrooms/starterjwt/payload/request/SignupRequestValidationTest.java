package com.openclassrooms.starterjwt.payload.request;

import jakarta.validation.ConstraintViolation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SignupRequestValidationTest {

    private Validator validator;

    @BeforeEach
    void setup() {

        ValidatorFactory factory =
                Validation.buildDefaultValidatorFactory();

        validator = factory.getValidator();
    }

    @Test
    void shouldValidateCorrectSignupRequest() {

        SignupRequest request =
                new SignupRequest();

        request.setEmail("test@test.com");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations =
                validator.validate(request);

        assertTrue(
                violations.isEmpty()
        );
    }

    @Test
    void shouldFailWhenEmailIsInvalid() {

        SignupRequest request =
                new SignupRequest();

        request.setEmail("invalid-email");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations =
                validator.validate(request);

        assertFalse(
                violations.isEmpty()
        );
    }

    @Test
    void shouldFailWhenEmailIsBlank() {

        SignupRequest request =
                new SignupRequest();

        request.setEmail("");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations =
                validator.validate(request);

        assertFalse(
                violations.isEmpty()
        );
    }

    @Test
    void shouldFailWhenFirstNameTooShort() {

        SignupRequest request =
                new SignupRequest();

        request.setEmail("test@test.com");
        request.setFirstName("Jo");
        request.setLastName("Doe");
        request.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations =
                validator.validate(request);

        assertFalse(
                violations.isEmpty()
        );
    }

    @Test
    void shouldFailWhenLastNameIsBlank() {

        SignupRequest request =
                new SignupRequest();

        request.setEmail("test@test.com");
        request.setFirstName("John");
        request.setLastName("");
        request.setPassword("password123");

        Set<ConstraintViolation<SignupRequest>> violations =
                validator.validate(request);

        assertFalse(
                violations.isEmpty()
        );
    }

    @Test
    void shouldFailWhenPasswordTooShort() {

        SignupRequest request =
                new SignupRequest();

        request.setEmail("test@test.com");
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setPassword("123");

        Set<ConstraintViolation<SignupRequest>> violations =
                validator.validate(request);

        assertFalse(
                violations.isEmpty()
        );
    }

    @Test
    void shouldFailWhenAllFieldsAreNull() {

        SignupRequest request =
                new SignupRequest();

        Set<ConstraintViolation<SignupRequest>> violations =
                validator.validate(request);

        assertEquals(
                4,
                violations.size()
        );
    }
}