package com.openclassrooms.starterjwt.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    @Test
    void shouldTestSettersAndGetters() {

        LocalDateTime now = LocalDateTime.now();

        UserDto userDto = new UserDto();

        userDto.setId(1L);
        userDto.setEmail("test@test.com");
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setPassword("password");
        userDto.setAdmin(false);
        userDto.setCreatedAt(now);
        userDto.setUpdatedAt(now);

        assertEquals(1L, userDto.getId());
        assertEquals("test@test.com", userDto.getEmail());
        assertEquals("John", userDto.getFirstName());
        assertEquals("Doe", userDto.getLastName());
        assertEquals("password", userDto.getPassword());

        assertFalse(userDto.isAdmin());

        assertEquals(now, userDto.getCreatedAt());
        assertEquals(now, userDto.getUpdatedAt());
    }

    @Test
    void shouldTestEqualsAndHashCode() {

        LocalDateTime now = LocalDateTime.now();

        UserDto user1 = new UserDto();

        user1.setId(1L);
        user1.setEmail("test@test.com");
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setPassword("password");
        user1.setAdmin(false);
        user1.setCreatedAt(now);
        user1.setUpdatedAt(now);

        UserDto user2 = new UserDto();

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

        UserDto user1 = new UserDto();
        user1.setId(1L);

        UserDto user2 = new UserDto();
        user2.setId(2L);

        assertNotEquals(user1, user2);
    }
}