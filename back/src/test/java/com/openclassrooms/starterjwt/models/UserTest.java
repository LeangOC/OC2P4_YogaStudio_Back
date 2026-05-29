package com.openclassrooms.starterjwt.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldTestSettersAndGetters() {

        LocalDateTime now = LocalDateTime.now();

        User user = new User();

        user.setId(1L);
        user.setEmail("test@test.com");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setAdmin(false);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        assertEquals(1L, user.getId());

        assertEquals("test@test.com", user.getEmail());

        assertEquals("John", user.getFirstName());

        assertEquals("Doe", user.getLastName());

        assertEquals("password", user.getPassword());

        assertFalse(user.isAdmin());

        assertEquals(now, user.getCreatedAt());

        assertEquals(now, user.getUpdatedAt());
    }

    @Test
    void shouldTestEqualsAndHashCode() {

        LocalDateTime now = LocalDateTime.now();

        User user1 = new User();

        user1.setId(1L);
        user1.setEmail("test@test.com");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setPassword("password");
        user1.setAdmin(false);
        user1.setCreatedAt(now);
        user1.setUpdatedAt(now);

        User user2 = new User();

        user2.setId(1L);
        user2.setEmail("test@test.com");
        user2.setFirstName("John");
        user2.setLastName("Doe");
        user2.setPassword("password");
        user2.setAdmin(false);
        user2.setCreatedAt(now);
        user2.setUpdatedAt(now);

        assertEquals(user1, user2);

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void shouldTestNotEquals() {

        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(2L);

        assertNotEquals(user1, user2);
    }

    @Test
    void shouldTestBuilder() {

        LocalDateTime now = LocalDateTime.now();

        User user = User.builder()
                .id(1L)
                .email("test@test.com")
                .firstName("John")
                .lastName("Doe")
                .password("password")
                .admin(false)
                .createdAt(now)
                .updatedAt(now)
                .build();

        assertNotNull(user);

        assertEquals(1L, user.getId());

        assertEquals("test@test.com", user.getEmail());

        assertEquals("John", user.getFirstName());

        assertEquals("Doe", user.getLastName());

        assertEquals("password", user.getPassword());

        assertFalse(user.isAdmin());

        assertEquals(now, user.getCreatedAt());

        assertEquals(now, user.getUpdatedAt());
    }
}