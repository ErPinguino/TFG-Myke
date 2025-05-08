// src/main/java/com/tfg/tfg/Service/UserService.java
package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.User;
import com.tfg.tfg.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired private UserRepository users;
    @Autowired private PasswordEncoder encoder;

    /** Registra un usuario nuevo, lanzando excepción si ya existe */
    public User register(String username, String rawPassword) {
        if (users.findByName(username).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese nombre");
        }
        User u = User.builder()
                .name(username)
                .password(encoder.encode(rawPassword))
                .build();
        return users.save(u);
    }

    /** Carga el usuario para Spring Security */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = users.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getName())
                .password(u.getPassword())
                .roles("USER")
                .build();
    }
    public User findByName(String username) {
        return users.findByName(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + username));
    }
}
