package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.SeedInPutDTO;
import com.tfg.tfg.DTOs.SeedOutPutDTO;
import com.tfg.tfg.Entities.Seed;
import com.tfg.tfg.Services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/minecraftProject/seeds")
public class SeedController {

    private final SeedService seedService;

    @Autowired
    public SeedController(SeedService seedService) {
        this.seedService = seedService;
    }

    // Crear un nuevo Seed
    @PostMapping
    public ResponseEntity<SeedOutPutDTO> createSeed(@RequestBody SeedInPutDTO seedInPutDTO) {
        Seed seed = new Seed();
        seed.setSeed_value(seedInPutDTO.getSeed_value());
        // Aquí deberías mapear los resourceLocations si es necesario
        Seed createdSeed = seedService.createSeed(seed);
        SeedOutPutDTO seedOutPutDTO = SeedOutPutDTO.fromEntity(createdSeed);
        return new ResponseEntity<>(seedOutPutDTO, HttpStatus.CREATED);
    }

    // Obtener todos los Seed
    @GetMapping
    public ResponseEntity<List<SeedOutPutDTO>> getAllSeeds() {
        List<Seed> seeds = seedService.getAllSeeds();
        List<SeedOutPutDTO> seedOutPutDTOs = seeds.stream()
                .map(SeedOutPutDTO::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(seedOutPutDTOs, HttpStatus.OK);
    }

    // Obtener un Seed por su ID
    @GetMapping("/{id}")
    public ResponseEntity<SeedOutPutDTO> getSeedById(@PathVariable Long id) {
        Optional<Seed> optionalSeed = seedService.getSeedById(id);
        if (optionalSeed.isPresent()) {
            SeedOutPutDTO seedOutPutDTO = SeedOutPutDTO.fromEntity(optionalSeed.get());
            return new ResponseEntity<>(seedOutPutDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar un Seed existente
    @PutMapping("/{id}")
    public ResponseEntity<SeedOutPutDTO> updateSeed(@PathVariable Long id, @RequestBody SeedInPutDTO seedInPutDTO) {
        Optional<Seed> optionalSeed = seedService.getSeedById(id);
        if (optionalSeed.isPresent()) {
            Seed existingSeed = optionalSeed.get();
            existingSeed.setSeed_value(seedInPutDTO.getSeed_value());
            // Aquí deberías mapear los resourceLocations si es necesario
            Seed updatedSeed = seedService.updateSeed(id, existingSeed);
            SeedOutPutDTO seedOutPutDTO = SeedOutPutDTO.fromEntity(updatedSeed);
            return new ResponseEntity<>(seedOutPutDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un Seed por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeed(@PathVariable Long id) {
        seedService.deleteSeed(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Obtener todos los Seed asociados a un User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SeedOutPutDTO>> getSeedsByUser(@PathVariable Long userId) {
        List<Seed> seeds = seedService.getSeedsByUser(userId);
        List<SeedOutPutDTO> seedOutPutDTOs = seeds.stream()
                .map(SeedOutPutDTO::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(seedOutPutDTOs, HttpStatus.OK);
    }
}