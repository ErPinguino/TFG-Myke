package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.BiomeOutPutDTO;
import com.tfg.tfg.Entities.Biome;
import com.tfg.tfg.Services.BiomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/minecraftProject/biomes")
@CrossOrigin(origins = "*")
public class BiomeController {

    @Autowired
    private BiomeService biomeService;

    @PostMapping
    public ResponseEntity<BiomeOutPutDTO> createBiome(@RequestBody Biome biome) {
        Biome createdBiome = biomeService.createBiome(biome);
        return new ResponseEntity<>(BiomeOutPutDTO.fromEntity(createdBiome), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BiomeOutPutDTO> getBiomeById(@PathVariable Long id) {
        Biome biome = biomeService.findBiomeById(id);
        return ResponseEntity.ok(BiomeOutPutDTO.fromEntity(biome));
    }

    @GetMapping
    public ResponseEntity<List<BiomeOutPutDTO>> getAllBiomes() {
        List<BiomeOutPutDTO> biomes = biomeService.findAllBiomes()
                .stream()
                .map(BiomeOutPutDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(biomes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BiomeOutPutDTO> updateBiome(@PathVariable Long id, @RequestBody Biome biome) {
        Biome updatedBiome = biomeService.updateBiome(id, biome);
        return ResponseEntity.ok(BiomeOutPutDTO.fromEntity(updatedBiome));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBiome(@PathVariable Long id) {
        biomeService.deleteBiome(id);
        return ResponseEntity.noContent().build();
    }
}