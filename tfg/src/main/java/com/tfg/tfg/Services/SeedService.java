// src/main/java/com/tfg/tfg/Service/SeedService.java
package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.Seed;
import com.tfg.tfg.Entities.User;
import com.tfg.tfg.Repository.SeedRepository;
import com.tfg.tfg.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeedService {
    @Autowired private SeedRepository seeds;
    @Autowired private UserRepository users;

    /** Lista todas las semillas de un usuario */
    public List<Seed> findByUser(Long userId) {
        return seeds.findByUserId(userId);
    }

    /** Añade una nueva semilla para el usuario */
    public Seed save(Long userId, String seedValue) {
        User u = users.findById(userId).orElseThrow();
        Seed s = new Seed();
        s.setSeed_value(seedValue);
        s.setUser(u);
        s.setCreated_at(LocalDateTime.now());
        return seeds.save(s);
    }

    /** Elimina una semilla si pertenece al usuario */
    public void delete(Long userId, Long seedId) {
        Seed s = seeds.findById(seedId)
                .filter(seed -> seed.getUser().getId().equals(userId))
                .orElseThrow(() -> new RuntimeException("Not found or not yours"));
        seeds.delete(s);
    }
}
