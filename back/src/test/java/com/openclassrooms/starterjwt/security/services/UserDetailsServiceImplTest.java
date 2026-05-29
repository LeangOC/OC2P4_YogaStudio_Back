package com.openclassrooms.starterjwt.security.services;

import com.openclassrooms.starterjwt.models.User;
import com.openclassrooms.starterjwt.repository.UserRepository;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void shouldLoadUserByUsername() {

        User user =
                User.builder()
                        .id(1L)
                        .email("test@test.com")
                        .firstName("John")
                        .lastName("Doe")
                        .password("password")
                        .admin(false)
                        .build();

        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(user));

        UserDetailsImpl userDetails =
                (UserDetailsImpl)
                        userDetailsService.loadUserByUsername(
                                "test@test.com"
                        );

        assertNotNull(userDetails);

        assertEquals(
                user.getId(),
                userDetails.getId()
        );

        assertEquals(
                user.getEmail(),
                userDetails.getUsername()
        );

        assertEquals(
                user.getFirstName(),
                userDetails.getFirstName()
        );

        assertEquals(
                user.getLastName(),
                userDetails.getLastName()
        );

        assertEquals(
                user.getPassword(),
                userDetails.getPassword()
        );

        assertEquals(
                user.isAdmin(),
                userDetails.getAdmin()
        );
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        when(userRepository.findByEmail(
                "unknown@test.com"
        )).thenReturn(Optional.empty());

        assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(
                        "unknown@test.com"
                )
        );
    }
}