package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.BiomeInPutDTO;
import com.tfg.tfg.DTOs.BiomeListOutPutDTO;
import com.tfg.tfg.Services.BiomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/minecraftProject/biomes")
@CrossOrigin(origins = "*")
public class BiomeController {

    @Autowired
    private BiomeService biomeService;

    /**
     * POST a /search con body JSON de BiomeInPutDTO
     * Ejemplo:
     * {
     *   "seed": 123456789,
     *   "name": "plains",
     *   "radius": 2000,
     *   "step": 16,
     *   "y": 63
     * }
     */
    @PostMapping("/search")
    public ResponseEntity<BiomeListOutPutDTO> searchBiome(@RequestBody BiomeInPutDTO input) {
        BiomeListOutPutDTO result = biomeService.findBiomes(input);
        return ResponseEntity.ok(result);
    }
}
