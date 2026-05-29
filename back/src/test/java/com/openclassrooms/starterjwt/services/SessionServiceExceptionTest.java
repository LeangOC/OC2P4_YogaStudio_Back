package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.models.Session;
import com.openclassrooms.starterjwt.models.User;

import com.openclassrooms.starterjwt.repository.SessionRepository;
import com.openclassrooms.starterjwt.repository.TeacherRepository;
import com.openclassrooms.starterjwt.repository.UserRepository;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessionServiceExceptionTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SessionService sessionService;

    @Test
    void shouldThrowWhenSessionNotFoundForParticipate() {

        when(sessionRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> sessionService.participate(1L, 1L)
        );
    }

    @Test
    void shouldThrowWhenUserNotFoundForParticipate() {

        Session session =
                new Session();

        session.setUsers(new ArrayList<>());

        when(sessionRepository.findById(1L))
                .thenReturn(Optional.of(session));

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> sessionService.participate(1L, 1L)
        );
    }

    @Test
    void shouldAddUserToSession() {

        User user =
                User.builder()
                        .id(1L)
                        .email("test@test.com")
                        .firstName("John")
                        .lastName("Doe")
                        .password("password")
                        .admin(false)
                        .build();

        Session session =
                new Session();

        session.setUsers(new ArrayList<>());

        when(sessionRepository.findById(1L))
                .thenReturn(Optional.of(session));

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        sessionService.participate(1L, 1L);

        assertEquals(
                1,
                session.getUsers().size()
        );

        verify(sessionRepository)
                .save(session);
    }

    @Test
    void shouldRemoveUserFromSession() {

        User user =
                User.builder()
                        .id(1L)
                        .email("test@test.com")
                        .firstName("John")
                        .lastName("Doe")
                        .password("password")
                        .admin(false)
                        .build();

        Session session =
                new Session();

        ArrayList<User> users =
                new ArrayList<>();

        users.add(user);

        session.setUsers(users);

        when(sessionRepository.findById(1L))
                .thenReturn(Optional.of(session));

        sessionService.noLongerParticipate(
                1L,
                1L
        );

        assertTrue(
                session.getUsers().isEmpty()
        );

        verify(sessionRepository)
                .save(session);
    }

    @Test
    void shouldThrowWhenSessionNotFoundForRemoveParticipation() {

        when(sessionRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> sessionService.noLongerParticipate(
                        1L,
                        1L
                )
        );
    }
}