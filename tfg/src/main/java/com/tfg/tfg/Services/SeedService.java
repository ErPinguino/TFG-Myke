package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.Seed;
import com.tfg.tfg.Repository.SeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeedService {

    private final SeedRepository seedRepository;

    @Autowired
    public SeedService(SeedRepository seedRepository) {
        this.seedRepository = seedRepository;
    }

    // Crear un nuevo Seed
    public Seed createSeed(Seed seed) {
        return seedRepository.save(seed);
    }

    // Obtener todos los Seed
    public List<Seed> getAllSeeds() {
        return seedRepository.findAll();
    }

    // Obtener un Seed por su ID
    public Optional<Seed> getSeedById(Long id) {
        return seedRepository.findById(id);
    }

    // Actualizar un Seed existente
    public Seed updateSeed(Long id, Seed seedDetails) {
        Optional<Seed> optionalSeed = seedRepository.findById(id);
        if (optionalSeed.isPresent()) {
            Seed existingSeed = optionalSeed.get();
            existingSeed.setSeed_value(seedDetails.getSeed_value());
            existingSeed.setResourceLocationList(seedDetails.getResourceLocationList());
            existingSeed.setUser(seedDetails.getUser());
            return seedRepository.save(existingSeed);
        } else {
            throw new RuntimeException("Seed not found with id " + id);
        }
    }

    // Eliminar un Seed por su ID
    public void deleteSeed(Long id) {
        seedRepository.deleteById(id);
    }

    // Obtener todos los Seed asociados a un User
    public List<Seed> getSeedsByUser(Long userId) {
        return seedRepository.findByUserId(userId);
    }
}