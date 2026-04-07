package com.example.quiz.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CustomAuthenticationSuccessHandlerTest {

    private CustomAuthenticationSuccessHandler successHandler;
    private HttpServletRequest mockRequest;
    private HttpServletResponse mockResponse;

    @BeforeEach
    public void setUp() {
        successHandler = new CustomAuthenticationSuccessHandler();
        mockRequest = mock(HttpServletRequest.class);
        mockResponse = mock(HttpServletResponse.class);
    }

    private Authentication createAuthenticationWithRole(String role) {
        return new org.springframework.security.core.Authentication() {
            @Override
            public Set<GrantedAuthority> getAuthorities() {
                Set<GrantedAuthority> authorities = new HashSet<>();
                authorities.add(new SimpleGrantedAuthority(role));
                return authorities;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
            }

            @Override
            public String getName() {
                return "test_user";
            }
        };
    }

    @Test
    public void testOnAuthenticationSuccessWithUserRole() throws IOException, ServletException {
        // Arrange
        Authentication auth = createAuthenticationWithRole("ROLE_USER");

        // Act
        successHandler.onAuthenticationSuccess(mockRequest, mockResponse, auth);

        // Assert
        verify(mockResponse).sendRedirect("/quiz/quiz");
    }

    @Test
    public void testOnAuthenticationSuccessWithAdminRole() throws IOException, ServletException {
        // Arrange
        Authentication auth = createAuthenticationWithRole("ROLE_ADMIN");

        // Act
        successHandler.onAuthenticationSuccess(mockRequest, mockResponse, auth);

        // Assert
        verify(mockResponse).sendRedirect("/quiz/quizlist");
    }

    @Test
    public void testOnAuthenticationSuccessCallsResponseSendRedirect() throws IOException, ServletException {
        // Arrange
        Authentication auth = createAuthenticationWithRole("ROLE_USER");

        // Act
        successHandler.onAuthenticationSuccess(mockRequest, mockResponse, auth);

        // Assert
        verify(mockResponse, times(1)).sendRedirect(anyString());
    }

    @Test
    public void testSuccessHandlerCanBeInstantiated() {
        // Act
        CustomAuthenticationSuccessHandler handler = new CustomAuthenticationSuccessHandler();

        // Assert
        assertNotNull(handler);
        assertTrue(handler instanceof org.springframework.security.web.authentication.AuthenticationSuccessHandler);
    }
}
