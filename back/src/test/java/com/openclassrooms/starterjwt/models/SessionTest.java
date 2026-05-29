package com.openclassrooms.starterjwt.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void shouldTestSettersAndGetters() {

        LocalDateTime now = LocalDateTime.now();

        Teacher teacher = new Teacher();
        teacher.setId(1L);

        List<User> users = new ArrayList<>();

        User user = new User();
        user.setId(1L);

        users.add(user);

        Session session = new Session();

        session.setId(1L);
        session.setName("Yoga Session");
        session.setDescription("Yoga description");
        session.setDate(new Date());

        session.setTeacher(teacher);

        session.setUsers(users);

        session.setCreatedAt(now);
        session.setUpdatedAt(now);

        assertEquals(1L, session.getId());

        assertEquals("Yoga Session", session.getName());

        assertEquals("Yoga description", session.getDescription());

        assertNotNull(session.getDate());

        assertEquals(1L, session.getTeacher().getId());

        assertEquals(1, session.getUsers().size());

        assertEquals(now, session.getCreatedAt());

        assertEquals(now, session.getUpdatedAt());
    }

    @Test
    void shouldTestEqualsAndHashCode() {

        LocalDateTime now = LocalDateTime.now();

        Session session1 = new Session();

        session1.setId(1L);
        session1.setName("Yoga Session");
        session1.setDescription("Yoga description");
        session1.setDate(new Date());

        session1.setCreatedAt(now);
        session1.setUpdatedAt(now);

        Session session2 = new Session();

        session2.setId(1L);
        session2.setName("Yoga Session");
        session2.setDescription("Yoga description");
        session2.setDate(new Date());

        session2.setCreatedAt(now);
        session2.setUpdatedAt(now);

        assertEquals(session1, session2);

        assertEquals(session1.hashCode(), session2.hashCode());
    }

    @Test
    void shouldTestNotEquals() {

        Session session1 = new Session();
        session1.setId(1L);

        Session session2 = new Session();
        session2.setId(2L);

        assertNotEquals(session1, session2);
    }

    @Test
    void shouldTestBuilder() {

        LocalDateTime now = LocalDateTime.now();

        Teacher teacher = new Teacher();
        teacher.setId(1L);

        List<User> users = new ArrayList<>();

        User user = new User();
        user.setId(1L);

        users.add(user);

        Session session = Session.builder()
                .id(1L)
                .name("Yoga Session")
                .description("Yoga description")
                .date(new Date())
                .teacher(teacher)
                .users(users)
                .createdAt(now)
                .updatedAt(now)
                .build();

        assertNotNull(session);

        assertEquals(1L, session.getId());

        assertEquals("Yoga Session", session.getName());

        assertEquals("Yoga description", session.getDescription());

        assertNotNull(session.getDate());

        assertEquals(1L, session.getTeacher().getId());

        assertEquals(1, session.getUsers().size());

        assertEquals(now, session.getCreatedAt());

        assertEquals(now, session.getUpdatedAt());
    }

    @Test
    void shouldTestEqualsWithSameObject() {

        Session session = new Session();

        session.setId(1L);

        assertEquals(session, session);
    }

    @Test
    void shouldTestEqualsWithNull() {

        Session session = new Session();

        session.setId(1L);

        assertNotEquals(null, session);
    }

    @Test
    void shouldTestEqualsWithDifferentClass() {

        Session session = new Session();

        session.setId(1L);

        assertNotEquals(session, "session");
    }

    @Test
    void shouldTestEqualsWithNullIds() {

        Session session1 = new Session();

        Session session2 = new Session();

        assertEquals(session1, session2);
    }

    @Test
    void shouldTestEqualsWithOneNullId() {

        Session session1 = new Session();

        session1.setId(1L);

        Session session2 = new Session();

        assertNotEquals(session1, session2);
    }

    @Test
    void shouldTestHashCodeWithNullId() {

        Session session = new Session();

        int hashCode = session.hashCode();

        assertNotEquals(0, hashCode);
    }

    @Test
    void shouldTestHashCodeWithId() {

        Session session = new Session();

        session.setId(1L);

        int hashCode = session.hashCode();

        assertNotEquals(0, hashCode);
    }

    @Test
    void shouldTestEqualsWithDifferentFieldsButSameId() {

        Session session1 = new Session();

        session1.setId(1L);
        session1.setName("Yoga");
        session1.setDescription("Description 1");
        session1.setDate(new Date());

        Session session2 = new Session();

        session2.setId(1L);
        session2.setName("Pilates");
        session2.setDescription("Description 2");
        session2.setDate(new Date());

        assertEquals(session1, session2);
    }

    @Test
    void shouldTestToString() {

        Session session = new Session();

        session.setId(1L);
        session.setName("Yoga Session");
        session.setDescription("Yoga description");
        session.setDate(new Date());

        String result = session.toString();

        assertNotNull(result);

        assertTrue(result.contains("Yoga Session"));
        assertTrue(result.contains("Yoga description"));
    }

    @Test
    void shouldTestChainableSetters() {

        Session session = new Session()
                .setId(1L)
                .setName("Yoga Session")
                .setDescription("Description")
                .setDate(new Date());

        assertEquals(1L, session.getId());
        assertEquals("Yoga Session", session.getName());
        assertEquals("Description", session.getDescription());
        assertNotNull(session.getDate());
    }
}