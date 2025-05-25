package com.tfg.tfg.controller;

import com.tfg.tfg.entities.User;
import com.tfg.tfg.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private AuthController authController;

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("Given valid registration data, when register is called, then returns the created User")
    void givenValidAuthRequest_whenRegister_thenReturnsUser() {
        // Given
        AuthController.AuthRequest authReq = new AuthController.AuthRequest();
        authReq.setUsername("alice");
        authReq.setPassword("s3cr3t");

        User builtUser = User.builder()
                .id(1L)
                .name("alice")
                .password("s3cr3t")
                .build();
        given(userService.register("alice", "s3cr3t")).willReturn(builtUser);

        // When
        ResponseEntity<User> response = authController.register(authReq);

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isSameAs(builtUser);
        assertThat(response.getBody().getName()).isEqualTo("alice");
        assertThat(response.getBody().getId()).isEqualTo(1L);
        then(userService).should(times(1)).register("alice", "s3cr3t");
    }

    @Test
    @DisplayName("Given valid login credentials, when login is called, then authenticates and stores context in session")
    void givenValidCredentials_whenLogin_thenAuthenticateAndStoreSession() {
        // Given
        AuthController.LoginRequest loginReq = new AuthController.LoginRequest();
        loginReq.setUsername("bob");
        loginReq.setPassword("p@ssw0rd");

        given(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .willReturn(authentication);
        given(request.getSession(true)).willReturn(session);

        // When
        ResponseEntity<Map<String, String>> response = authController.login(loginReq, request);

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).containsEntry("message", "Login exitoso");

        ArgumentCaptor<UsernamePasswordAuthenticationToken> tokenCaptor =
                ArgumentCaptor.forClass(UsernamePasswordAuthenticationToken.class);
        then(authenticationManager).should().authenticate(tokenCaptor.capture());

        UsernamePasswordAuthenticationToken captured = tokenCaptor.getValue();
        assertThat(captured.getPrincipal()).isEqualTo("bob");
        assertThat(captured.getCredentials()).isEqualTo("p@ssw0rd");

        then(session).should().setAttribute(
                eq(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY),
                same(SecurityContextHolder.getContext())
        );
    }

    @Test
    @DisplayName("Given an existing session, when logout is called, then invalidates session and clears security context")
    void givenExistingSession_whenLogout_thenInvalidateAndClearContext() {
        // Given
        given(request.getSession(false)).willReturn(session);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // When
        ResponseEntity<Void> response = authController.logout(request);

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        then(session).should().invalidate();
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    @DisplayName("Given no session exists, when logout is called, then only clears security context")
    void givenNoSession_whenLogout_thenOnlyClearContext() {
        // Given
        given(request.getSession(false)).willReturn(null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // When
        ResponseEntity<Void> response = authController.logout(request);

        // Then
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        then(session).should(never()).invalidate();
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }
}
