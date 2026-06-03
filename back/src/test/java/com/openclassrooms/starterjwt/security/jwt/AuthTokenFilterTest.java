package com.openclassrooms.starterjwt.security.jwt;

import com.openclassrooms.starterjwt.security.services.UserDetailsImpl;
import com.openclassrooms.starterjwt.security.services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthTokenFilterTest {

    @InjectMocks
    private AuthTokenFilter authTokenFilter;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    void setup() {

        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldAuthenticateUserWithValidJwt()
            throws Exception {

        String jwt = "valid-jwt-token";

        when(request.getHeader("Authorization"))
                .thenReturn("Bearer " + jwt);

        when(jwtUtils.validateJwtToken(jwt))
                .thenReturn(true);

        when(jwtUtils.getUserNameFromJwtToken(jwt))
                .thenReturn("test@test.com");

        UserDetailsImpl userDetails =
                new UserDetailsImpl(
                        1L,
                        "test@test.com",
                        "John",
                        "Doe",
                        false,
                        "password"
                );

        when(userDetailsService.loadUserByUsername(
                "test@test.com"
        )).thenReturn(userDetails);

        authTokenFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        assertNotNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        assertEquals(
                "test@test.com",
                ((UserDetailsImpl)
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal())
                        .getUsername()
        );

        verify(filterChain)
                .doFilter(request, response);
    }

    @Test
    void shouldContinueFilterWhenJwtInvalid()
            throws Exception {

        String jwt = "invalid-jwt";

        when(request.getHeader("Authorization"))
                .thenReturn("Bearer " + jwt);

        when(jwtUtils.validateJwtToken(jwt))
                .thenReturn(false);

        authTokenFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        assertNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        verify(filterChain)
                .doFilter(request, response);
    }

    @Test
    void shouldContinueFilterWhenAuthorizationHeaderMissing()
            throws Exception {

        when(request.getHeader("Authorization"))
                .thenReturn(null);

        authTokenFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        assertNull(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        verify(filterChain)
                .doFilter(request, response);
    }

    @Test
    void shouldHandleException()
            throws Exception {

        when(request.getHeader("Authorization"))
                .thenThrow(
                        new RuntimeException("Test exception")
                );

        authTokenFilter.doFilterInternal(
                request,
                response,
                filterChain
        );

        verify(filterChain)
                .doFilter(request, response);
    }
}