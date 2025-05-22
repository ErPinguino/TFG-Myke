package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.SeedInPutDTO;
import com.tfg.tfg.DTOs.SeedOutPutDTO;
import com.tfg.tfg.Entities.Seed;
import com.tfg.tfg.Services.SeedService;
import com.tfg.tfg.Services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/minecraftProject/seeds")
public class SeedController {

    private static final Logger log = LoggerFactory.getLogger(SeedController.class);

    private final SeedService seedService;
    private final UserService userService;

    public SeedController(SeedService seedService, UserService userService) {
        this.seedService = seedService;
        this.userService = userService;
    }

    /**
     * 1) Listar todas las seeds del usuario
     */
    @GetMapping({"", "/"})
    public ResponseEntity<List<SeedOutPutDTO>> list(Authentication auth) {
        log.info("[SEEDS] GET /seeds → auth={}", auth);
        if (auth == null) {
            log.warn("[SEEDS] Authentication es null");
        } else {
            log.info("[SEEDS] isAuthenticated={} principal={}", auth.isAuthenticated(), auth.getPrincipal());
        }

        String username = auth.getName();
        Long userId = userService.findByName(username).getId();
        List<SeedOutPutDTO> dtos = seedService.findByUser(userId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * 2) Añadir una nueva semilla
     */
    @PostMapping({"", "/"})
    public ResponseEntity<SeedOutPutDTO> add(
            Authentication auth,
            @RequestBody SeedInPutDTO input
    ) {
        log.info("[SEEDS] POST /seeds → auth={}", auth);
        if (auth == null) {
            log.warn("[SEEDS] Authentication es null");
        } else {
            log.info("[SEEDS] isAuthenticated={} principal={}", auth.isAuthenticated(), auth.getPrincipal());
        }

        Long userId = userService.findByName(auth.getName()).getId();
        String name = input.getName();
        if (name == null || name.isEmpty()) {
            name = input.getName();
        }
        Seed saved = seedService.save(userId, input.getSeed_value(), name);
        return ResponseEntity
                .status(201)
                .body(toDto(saved));
    }

    /**
     * 3) Eliminar una semilla
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            Authentication auth,
            @PathVariable Long id
    ) {
        log.info("[SEEDS] DELETE /seeds/{} → auth={}", id, auth);
        if (auth == null) {
            log.warn("[SEEDS] Authentication es null");
        } else {
            log.info("[SEEDS] isAuthenticated={} principal={}", auth.isAuthenticated(), auth.getPrincipal());
        }

        Long userId = userService.findByName(auth.getName()).getId();
        seedService.delete(userId, id);
        return ResponseEntity.noContent().build();
    }

    private SeedOutPutDTO toDto(Seed s) {
        return SeedOutPutDTO.builder()
                .id(s.getId())
                .seed_value(s.getSeed_value())
                .name(s.getName())
                .build();
    }
}

