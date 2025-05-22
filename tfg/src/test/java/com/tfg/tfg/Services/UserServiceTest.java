package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.User;
import com.tfg.tfg.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository users;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserService service;

    @Test
    void register() {
        // Given
        String username = "newUser";
        String rawPassword = "plain";
        given(users.findByName(username)).willReturn(Optional.empty());
        given(encoder.encode(rawPassword)).willReturn("encodedPwd");
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName(username);
        savedUser.setPassword("encodedPwd");
        given(users.save(any(User.class))).willReturn(savedUser);

        // When
        User result = service.register(username, rawPassword);

        // Then
        then(users).should().findByName(username);
        then(encoder).should().encode(rawPassword);
        then(users).should().save(any(User.class));
        assertSame(savedUser, result);
    }

    @Test
    void loadUserByUsername() {
        // Given
        String username = "existing";
        User u = new User();
        u.setId(2L);
        u.setName(username);
        u.setPassword("secret");
        given(users.findByName(username)).willReturn(Optional.of(u));

        // When
        UserDetails ud = service.loadUserByUsername(username);

        // Then
        then(users).should().findByName(username);
        assertEquals(username, ud.getUsername());
        assertEquals("secret", ud.getPassword());
        assertTrue(ud.getAuthorities().stream()
                .anyMatch(a -> "ROLE_USER".equals(a.getAuthority())));
    }

    @Test
    void findByName() {
        // Given
        String username = "findMe";
        User u = new User();
        u.setId(3L);
        u.setName(username);
        given(users.findByName(username)).willReturn(Optional.of(u));

        // When
        User result = service.findByName(username);

        // Then
        then(users).should().findByName(username);
        assertSame(u, result);
    }
}
