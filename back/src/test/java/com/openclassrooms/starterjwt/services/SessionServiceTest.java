package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.exception.BadRequestException;
import com.openclassrooms.starterjwt.exception.NotFoundException;
import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.SessionRepository;
import com.openclassrooms.starterjwt.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SessionService sessionService;

    private Session session;
    private User user;

    @BeforeEach
    void setup() {
        user = User.builder()
                .id(1L)
                .email("test@test.com")
                .firstName("John")
                .lastName("Doe")
                .password("password")
                .admin(false)
                .build();

        session = Session.builder()
                .id(1L)
                .name("Yoga")
                .users(new ArrayList<>())
                .build();
    }

    @Test
    void shouldCreateSession() {
        when(sessionRepository.save(session)).thenReturn(session);

        Session result = sessionService.create(session);

        assertNotNull(result);
        assertEquals("Yoga", result.getName());

        verify(sessionRepository).save(session);
    }

    @Test
    void shouldFindSessionById() {
        when(sessionRepository.findById(1L))
                .thenReturn(Optional.of(session));

        Session result = sessionService.getById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void shouldThrowNotFoundWhenSessionDoesNotExist() {
        when(sessionRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> sessionService.getById(1L));
    }

    @Test
    void shouldParticipateToSession() {
        when(sessionRepository.findById(1L))
                .thenReturn(Optional.of(session));

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        sessionService.participate(1L, 1L);

        assertEquals(1, session.getUsers().size());

        verify(sessionRepository).save(session);
    }

    @Test
    void shouldThrowBadRequestWhenAlreadyParticipating() {
        session.setUsers(List.of(user));

        when(sessionRepository.findById(1L))
                .thenReturn(Optional.of(session));

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        assertThrows(BadRequestException.class,
                () -> sessionService.participate(1L, 1L));
    }

    @Test
    void shouldRemoveParticipant() {
        session.setUsers(new ArrayList<>(List.of(user)));

        when(sessionRepository.findById(1L))
                .thenReturn(Optional.of(session));

        sessionService.noLongerParticipate(1L, 1L);

        assertTrue(session.getUsers().isEmpty());

        verify(sessionRepository).save(session);
    }

    @Test
    void shouldThrowBadRequestWhenUserNotParticipating() {
        when(sessionRepository.findById(1L))
                .thenReturn(Optional.of(session));

        assertThrows(BadRequestException.class,
                () -> sessionService.noLongerParticipate(1L, 1L));
    }

// ajout
@Test
void shouldDeleteSession() {

    when(sessionRepository.findById(1L))
            .thenReturn(Optional.of(session));

    sessionService.delete(1L);

    verify(sessionRepository)
            .delete(session);
}
    @Test
    void shouldFindAllSessions() {

        List<Session> sessions =
                List.of(session);

        when(sessionRepository.findAll())
                .thenReturn(sessions);

        List<Session> result =
                sessionService.findAll();

        assertEquals(1, result.size());

        verify(sessionRepository)
                .findAll();
    }

    @Test
    void shouldUpdateSession() {

        Session updatedSession =
                Session.builder()
                        .name("Pilates")
                        .users(new ArrayList<>())
                        .build();

        when(sessionRepository.save(any(Session.class)))
                .thenReturn(updatedSession);

        Session result =
                sessionService.update(
                        1L,
                        updatedSession
                );

        assertEquals(
                "Pilates",
                result.getName()
        );

        assertEquals(
                1L,
                updatedSession.getId()
        );

        verify(sessionRepository)
                .save(updatedSession);
    }

    @Test
    void shouldThrowNotFoundWhenParticipatingUserDoesNotExist() {

        when(sessionRepository.findById(1L))
                .thenReturn(Optional.of(session));

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> sessionService.participate(1L, 1L)
        );
    }

    @Test
    void shouldThrowNotFoundWhenRemovingParticipationFromUnknownSession() {

        when(sessionRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                NotFoundException.class,
                () -> sessionService.noLongerParticipate(1L, 1L)
        );
    }



}