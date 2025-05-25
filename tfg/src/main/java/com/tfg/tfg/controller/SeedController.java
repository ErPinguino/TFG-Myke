package com.tfg.tfg.controller;

import com.tfg.tfg.dto.SeedInPutDTO;
import com.tfg.tfg.dto.SeedOutPutDTO;
import com.tfg.tfg.entities.Seed;
import com.tfg.tfg.services.SeedService;
import com.tfg.tfg.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/minecraftProject/seeds")
public class SeedController {

    private static final Logger log = LoggerFactory.getLogger(SeedController.class);

    private static final String WARN_AUTH_NULL    = "[SEEDS] Authentication es null o no autenticada";
    private static final String INFO_AUTH_STATUS = "[SEEDS] isAuthenticated={} principal={}";

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
        String username = requireAuth(auth);
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
        String username = requireAuth(auth);
        Long userId = userService.findByName(username).getId();

        String name = input.getName();
        if (name == null || name.isEmpty()) {
            name = input.getName();
        }
        Seed saved = seedService.save(userId, input.getSeedValue(), name);
        return ResponseEntity
                .status(HttpStatus.CREATED)
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
        String username = requireAuth(auth);
        Long userId = userService.findByName(username).getId();
        seedService.delete(userId, id);
        return ResponseEntity.noContent().build();
    }

    private SeedOutPutDTO toDto(Seed s) {
        return SeedOutPutDTO.builder()
                .id(s.getId())
                .seedValue(s.getSeedValue())
                .name(s.getName())
                .build();
    }

    /**
     * Valida que Authentication no sea null y esté autenticada.
     * Lanza 401 si falla.
     */
    private String requireAuth(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            log.warn(WARN_AUTH_NULL);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
        }
        log.info(INFO_AUTH_STATUS, auth.isAuthenticated(), auth.getPrincipal());
        return auth.getName();
    }
}
