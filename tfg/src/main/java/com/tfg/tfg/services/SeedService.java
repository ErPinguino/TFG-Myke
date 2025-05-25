// src/main/java/com/tfg/tfg/Service/SeedService.java
package com.tfg.tfg.services;

import com.tfg.tfg.entities.Seed;
import com.tfg.tfg.entities.User;
import com.tfg.tfg.repository.SeedRepository;
import com.tfg.tfg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeedService {
    private final SeedRepository seeds;
    private final UserRepository users;

    @Autowired
    public SeedService(SeedRepository seeds, UserRepository users) {
        this.seeds = seeds;
        this.users = users;
    }

    /** Lista todas las semillas de un usuario */
    public List<Seed> findByUser(Long userId) {
        return seeds.findByUserId(userId);
    }

    /** Añade una nueva semilla para el usuario */
    public Seed save(Long userId, String seedValue, String name) {
        User u = users.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));

        String alias = (name != null && !name.isBlank())
                ? name
                : seedValue;

        Seed s = Seed.builder()
                .seedValue(seedValue)
                .createdAt(LocalDateTime.now())
                .user(u)
                .name(alias)
                .build();
        return seeds.save(s);
    }

    /** Elimina una semilla si pertenece al usuario */
    public void delete(Long userId, Long seedId) {
        Seed s = seeds.findById(seedId)
                .orElseThrow(() -> new IllegalArgumentException("Seed no encontrada"));
        if (!s.getUser().getId().equals(userId)) {
            throw new SecurityException("No puedes eliminar esta semilla");
        }
        seeds.delete(s);
    }

}
