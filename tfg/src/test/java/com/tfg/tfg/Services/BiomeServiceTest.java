package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.Biome;
import com.tfg.tfg.Repository.BiomeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BiomeServiceTest {
    @Mock
    private BiomeRepository biomeRepository;

    @InjectMocks
    private BiomeService biomeService;

    private Biome biome;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        biome = new Biome();
        biome.setId(1L);
        biome.setName("Desert");
    }

    @Test
    void createBiome() {
        when(biomeRepository.save(any(Biome.class))).thenReturn(biome);
        Biome created = biomeService.createBiome(biome);
        assertEquals(biome.getName(), created.getName());
    }

    @Test
    void findBiomeById() {
        when(biomeRepository.findById(1L)).thenReturn(Optional.of(biome));
        Biome found = biomeService.findBiomeById(1L);
        assertEquals(biome.getName(), found.getName());
    }

    @Test
    void findAllBiomes() {
        when(biomeRepository.findAll()).thenReturn(Arrays.asList(biome));
        List<Biome> biomes = biomeService.findAllBiomes();
        assertEquals(1, biomes.size());
    }

    @Test
    void updateBiome() {
        when(biomeRepository.findById(1L)).thenReturn(Optional.of(biome));
        when(biomeRepository.save(any(Biome.class))).thenReturn(biome);

        Biome updated = biomeService.updateBiome(1L, biome);
        assertEquals(biome.getName(), updated.getName());
    }

    @Test
    void deleteBiome() {
        doNothing().when(biomeRepository).deleteById(1L);
        biomeService.deleteBiome(1L);
        verify(biomeRepository).deleteById(1L);
    }
}