package com.openclassrooms.starterjwt.security.services;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsImplTest {

    @Test
    void shouldTestConstructorAndGetters() {

        UserDetailsImpl userDetails =
                new UserDetailsImpl(
                        1L,
                        "test@test.com",
                        "John",
                        "Doe",
                        false,
                        "password"
                );

        assertNotNull(userDetails);

        assertEquals(1L, userDetails.getId());

        assertEquals(
                "test@test.com",
                userDetails.getUsername()
        );

        assertEquals(
                "password",
                userDetails.getPassword()
        );
    }

    @Test
    void shouldReturnAuthorities() {

        UserDetailsImpl userDetails =
                new UserDetailsImpl(
                        1L,
                        "test@test.com",
                        "John",
                        "Doe",
                        false,
                        "password"
                );

        assertNotNull(
                userDetails.getAuthorities()
        );

        assertTrue(
                userDetails.getAuthorities().isEmpty()
        );
    }

    @Test
    void shouldReturnTrueForAccountStatusMethods() {

        UserDetailsImpl userDetails =
                new UserDetailsImpl(
                        1L,
                        "test@test.com",
                        "John",
                        "Doe",
                        false,
                        "password"
                );

        assertTrue(
                userDetails.isAccountNonExpired()
        );

        assertTrue(
                userDetails.isAccountNonLocked()
        );

        assertTrue(
                userDetails.isCredentialsNonExpired()
        );

        assertTrue(
                userDetails.isEnabled()
        );
    }

    @Test
    void shouldTestEquals() {

        UserDetailsImpl user1 =
                new UserDetailsImpl(
                        1L,
                        "test@test.com",
                        "John",
                        "Doe",
                        false,
                        "password"
                );

        UserDetailsImpl user2 =
                new UserDetailsImpl(
                        1L,
                        "test@test.com",
                        "John",
                        "Doe",
                        false,
                        "password"
                );

        assertEquals(user1, user2);
    }

    @Test
    void shouldTestNotEquals() {

        UserDetailsImpl user1 =
                new UserDetailsImpl(
                        1L,
                        "test@test.com",
                        "John",
                        "Doe",
                        false,
                        "password"
                );

        UserDetailsImpl user2 =
                new UserDetailsImpl(
                        2L,
                        "other@test.com",
                        "Jane",
                        "Smith",
                        false,
                        "password"
                );

        assertNotEquals(user1, user2);
    }
}