package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.Biome;
import com.tfg.tfg.Repository.BiomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiomeService {

    @Autowired
    private BiomeRepository biomeRepository;

    public Biome createBiome(Biome biome) {
        return biomeRepository.save(biome);
    }

    public Biome findBiomeById(Long id) {
        return biomeRepository.findById(id).orElseThrow(() -> new RuntimeException("Biome not found"));
    }

    public List<Biome> findAllBiomes() {
        return biomeRepository.findAll();
    }

    public Biome updateBiome(Long id, Biome updatedBiome) {
        Biome existingBiome = findBiomeById(id);
        existingBiome.setName(updatedBiome.getName());
        return biomeRepository.save(existingBiome);
    }

    public void deleteBiome(Long id) {
        biomeRepository.deleteById(id);
    }
}