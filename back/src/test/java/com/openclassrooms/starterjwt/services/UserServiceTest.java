package com.openclassrooms.starterjwt.services;

import com.openclassrooms.starterjwt.exception.UnauthorizedException;
import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldDeleteUser() {

        User dbUser = User.builder()
                .id(1L)
                .email("test@test.com")
                .firstName("John")
                .lastName("Doe")
                .password("password")
                .admin(false)
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(dbUser));

        org.springframework.security.core.userdetails.User securityUser =
                new org.springframework.security.core.userdetails.User(
                        "test@test.com",
                        "password",
                        new ArrayList<>()
                );

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        securityUser,
                        null,
                        securityUser.getAuthorities()
                )
        );

        userService.delete(1L);

        verify(userRepository).delete(dbUser);
    }

    @Test
    void shouldThrowUnauthorizedException() {

        User dbUser = User.builder()
                .id(1L)
                .email("other@test.com")
                .firstName("John")
                .lastName("Doe")
                .password("password")
                .admin(false)
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(dbUser));

        org.springframework.security.core.userdetails.User securityUser =
                new org.springframework.security.core.userdetails.User(
                        "test@test.com",
                        "password",
                        new ArrayList<>()
                );

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        securityUser,
                        null,
                        securityUser.getAuthorities()
                )
        );

        assertThrows(
                UnauthorizedException.class,
                () -> userService.delete(1L)
        );
    }
}