package com.tfg.tfg.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/minecraftProject/world")
public class WorldController {

    // Debe coincidir con el working_dir de tu servicio backend en docker-compose.yml
    private static final File COMPOSE_DIR = new File("/host");

    private void run(String... cmd) throws IOException, InterruptedException {
        Process p = new ProcessBuilder(cmd)
                .directory(COMPOSE_DIR)
                .inheritIO()
                .start();
        if (p.waitFor() != 0) {
            throw new IllegalStateException("Error ejecutando: " + String.join(" ", cmd));
        }
    }

    @PostMapping("/regenerate")
    public ResponseEntity<?> regenerate(@RequestParam long seed) throws Exception {
        // Invocamos el script en /app/scripts
        run("./scripts/regen_world.sh", Long.toString(seed));
        return ResponseEntity.accepted().build();
    }
}

