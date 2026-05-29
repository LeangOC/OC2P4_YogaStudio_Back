package com.openclassrooms.starterjwt.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldTestNoArgsConstructor() {

        User user = new User();

        assertNotNull(user);
    }

    @Test
    void shouldTestRequiredArgsConstructor() {

        User user = new User(
                "test@test.com",
                "Doe",
                "John",
                "password",
                true
        );

        assertEquals("test@test.com", user.getEmail());
        assertEquals("Doe", user.getLastName());
        assertEquals("John", user.getFirstName());
        assertEquals("password", user.getPassword());
        assertTrue(user.isAdmin());
    }

    @Test
    void shouldTestAllArgsConstructor() {

        LocalDateTime now = LocalDateTime.now();

        User user = new User(
                1L,
                "test@test.com",
                "Doe",
                "John",
                "password",
                true,
                now,
                now
        );

        assertEquals(1L, user.getId());
        assertEquals("test@test.com", user.getEmail());
        assertEquals("Doe", user.getLastName());
        assertEquals("John", user.getFirstName());
        assertEquals("password", user.getPassword());
        assertTrue(user.isAdmin());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
    }

    @Test
    void shouldTestBuilder() {

        LocalDateTime now = LocalDateTime.now();

        User user = User.builder()
                .id(1L)
                .email("builder@test.com")
                .lastName("Builder")
                .firstName("Bob")
                .password("builderPassword")
                .admin(false)
                .createdAt(now)
                .updatedAt(now)
                .build();

        assertNotNull(user);

        assertEquals(1L, user.getId());
        assertEquals("builder@test.com", user.getEmail());
        assertEquals("Builder", user.getLastName());
        assertEquals("Bob", user.getFirstName());
        assertEquals("builderPassword", user.getPassword());
        assertFalse(user.isAdmin());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
    }

    @Test
    void shouldTestSettersAndGetters() {

        LocalDateTime now = LocalDateTime.now();

        User user = new User();

        user.setId(10L);
        user.setEmail("setter@test.com");
        user.setLastName("Setter");
        user.setFirstName("John");
        user.setPassword("password");
        user.setAdmin(true);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        assertEquals(10L, user.getId());
        assertEquals("setter@test.com", user.getEmail());
        assertEquals("Setter", user.getLastName());
        assertEquals("John", user.getFirstName());
        assertEquals("password", user.getPassword());
        assertTrue(user.isAdmin());
        assertEquals(now, user.getCreatedAt());
        assertEquals(now, user.getUpdatedAt());
    }

    @Test
    void shouldTestChainableSetters() {

        User user = new User()
                .setId(5L)
                .setEmail("chain@test.com")
                .setLastName("Chain")
                .setFirstName("able")
                .setPassword("chainPassword")
                .setAdmin(false);

        assertEquals(5L, user.getId());
        assertEquals("chain@test.com", user.getEmail());
        assertEquals("Chain", user.getLastName());
        assertEquals("able", user.getFirstName());
        assertEquals("chainPassword", user.getPassword());
        assertFalse(user.isAdmin());
    }

    @Test
    void shouldTestEqualsSameObject() {

        User user = new User();
        user.setId(1L);

        assertEquals(user, user);
    }

    @Test
    void shouldTestEqualsAndHashCode() {

        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(1L);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void shouldTestNotEqualsWithDifferentId() {

        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();
        user2.setId(2L);

        assertNotEquals(user1, user2);
    }

    @Test
    void shouldTestNotEqualsWithNull() {

        User user = new User();
        user.setId(1L);

        assertNotEquals(null, user);
    }

    @Test
    void shouldTestNotEqualsWithDifferentClass() {

        User user = new User();
        user.setId(1L);

        assertNotEquals(user, "test");
    }

    @Test
    void shouldTestEqualsWithNullIds() {

        User user1 = new User();
        User user2 = new User();

        assertEquals(user1, user2);
    }

    @Test
    void shouldTestEqualsWithOneNullId() {

        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();

        assertNotEquals(user1, user2);
    }

    @Test
    void shouldTestHashCode() {

        User user = new User();
        user.setId(1L);

        int hashCode = user.hashCode();

        assertNotEquals(0, hashCode);
    }

    @Test
    void shouldTestToString() {

        User user = new User();

        user.setId(1L);
        user.setEmail("string@test.com");
        user.setLastName("Doe");
        user.setFirstName("John");
        user.setPassword("password");
        user.setAdmin(true);

        String result = user.toString();

        assertNotNull(result);
        assertTrue(result.contains("string@test.com"));
        assertTrue(result.contains("Doe"));
        assertTrue(result.contains("John"));
        assertTrue(result.contains("password"));
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {

        assertThrows(NullPointerException.class, () ->
                new User(
                        null,
                        "Doe",
                        "John",
                        "password",
                        true
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsNull() {

        assertThrows(NullPointerException.class, () ->
                new User(
                        "test@test.com",
                        null,
                        "John",
                        "password",
                        true
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenFirstNameIsNull() {

        assertThrows(NullPointerException.class, () ->
                new User(
                        "test@test.com",
                        "Doe",
                        null,
                        "password",
                        true
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNull() {

        assertThrows(NullPointerException.class, () ->
                new User(
                        "test@test.com",
                        "Doe",
                        "John",
                        null,
                        true
                )
        );
    }

    @Test
    void shouldThrowExceptionWhenSetEmailWithNull() {

        User user = new User();

        assertThrows(NullPointerException.class, () ->
                user.setEmail(null)
        );
    }

    @Test
    void shouldThrowExceptionWhenSetLastNameWithNull() {

        User user = new User();

        assertThrows(NullPointerException.class, () ->
                user.setLastName(null)
        );
    }

    @Test
    void shouldThrowExceptionWhenSetFirstNameWithNull() {

        User user = new User();

        assertThrows(NullPointerException.class, () ->
                user.setFirstName(null)
        );
    }

    @Test
    void shouldThrowExceptionWhenSetPasswordWithNull() {

        User user = new User();

        assertThrows(NullPointerException.class, () ->
                user.setPassword(null)
        );
    }

    @Test
    void shouldTestEqualsWithDifferentValuesButSameId() {

        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("a@test.com");

        User user2 = new User();
        user2.setId(1L);
        user2.setEmail("b@test.com");

        assertEquals(user1, user2);
    }

    @Test
    void shouldTestEqualsWithNullIdOnBothObjects() {

        User user1 = new User();
        User user2 = new User();

        assertEquals(user1, user2);
    }

    @Test
    void shouldTestEqualsWithOneNullId2() {

        User user1 = new User();
        user1.setId(1L);

        User user2 = new User();

        assertNotEquals(user1, user2);
    }

    @Test
    void shouldTestEqualsWithSelf() {

        User user = new User();
        user.setId(1L);

        assertEquals(user, user);
    }

    @Test
    void shouldThrowExceptionWhenBuildWithoutRequiredFields() {

        assertThrows(NullPointerException.class, () ->
                User.builder().build()
        );
    }

    @Test
    void shouldThrowExceptionWhenBuilderEmailIsNull() {

        assertThrows(NullPointerException.class, () ->
                User.builder()
                        .email(null)
        );
    }

    @Test
    void shouldThrowExceptionWhenBuilderLastNameIsNull() {

        assertThrows(NullPointerException.class, () ->
                User.builder()
                        .lastName(null)
        );
    }

    @Test
    void shouldThrowExceptionWhenBuilderFirstNameIsNull() {

        assertThrows(NullPointerException.class, () ->
                User.builder()
                        .firstName(null)
        );
    }

    @Test
    void shouldThrowExceptionWhenBuilderPasswordIsNull() {

        assertThrows(NullPointerException.class, () ->
                User.builder()
                        .password(null)
        );
    }
}