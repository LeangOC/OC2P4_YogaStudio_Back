package com.openclassrooms.starterjwt.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SessionDtoTest {

    @Test
    void shouldTestSettersAndGetters() {

        LocalDateTime now = LocalDateTime.now();

        SessionDto sessionDto = new SessionDto();

        sessionDto.setId(1L);
        sessionDto.setName("Yoga Session");
        sessionDto.setDescription("Yoga description");
        sessionDto.setTeacher_id(1L);

        List<Long> users = new ArrayList<>();
        users.add(1L);

        sessionDto.setUsers(users);

        sessionDto.setCreatedAt(now);
        sessionDto.setUpdatedAt(now);

        assertEquals(1L, sessionDto.getId());
        assertEquals("Yoga Session", sessionDto.getName());
        assertEquals("Yoga description", sessionDto.getDescription());
        assertEquals(1L, sessionDto.getTeacher_id());

        assertEquals(1, sessionDto.getUsers().size());

        assertEquals(now, sessionDto.getCreatedAt());
        assertEquals(now, sessionDto.getUpdatedAt());
    }

    @Test
    void shouldTestEqualsAndHashCode() {

        LocalDateTime now = LocalDateTime.now();

        SessionDto session1 = new SessionDto();

        session1.setId(1L);
        session1.setName("Yoga Session");
        session1.setDescription("Yoga description");
        session1.setTeacher_id(1L);
        session1.setUsers(new ArrayList<>());
        session1.setCreatedAt(now);
        session1.setUpdatedAt(now);

        SessionDto session2 = new SessionDto();

        session2.setId(1L);
        session2.setName("Yoga Session");
        session2.setDescription("Yoga description");
        session2.setTeacher_id(1L);
        session2.setUsers(new ArrayList<>());
        session2.setCreatedAt(now);
        session2.setUpdatedAt(now);

        assertEquals(session1, session2);

        assertEquals(session1.hashCode(), session2.hashCode());
    }

    @Test
    void shouldTestNotEquals() {

        SessionDto session1 = new SessionDto();
        session1.setId(1L);

        SessionDto session2 = new SessionDto();
        session2.setId(2L);

        assertNotEquals(session1, session2);
    }
}