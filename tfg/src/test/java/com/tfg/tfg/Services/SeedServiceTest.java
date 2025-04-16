package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.Seed;
import com.tfg.tfg.Entities.User;
import com.tfg.tfg.Repository.SeedRepository;
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

class SeedServiceTest {
    @Mock
    private SeedRepository seedRepository;

    @InjectMocks
    private SeedService seedService;

    private Seed seed;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seed = new Seed();
        seed.setId(1L);
        seed.setSeed_value("12345");
    }

    @Test
    void createSeed() {
        when(seedRepository.save(any(Seed.class))).thenReturn(seed);
        Seed created = seedService.createSeed(seed);
        assertEquals(seed.getId(), created.getId());
        verify(seedRepository).save(any(Seed.class));
    }

    @Test
    void getAllSeeds() {
        List<Seed> seeds = Arrays.asList(seed);
        when(seedRepository.findAll()).thenReturn(seeds);
        List<Seed> found = seedService.getAllSeeds();
        assertEquals(1, found.size());
        verify(seedRepository).findAll();
    }

    @Test
    void getSeedById() {
        when(seedRepository.findById(1L)).thenReturn(Optional.of(seed));
        Optional<Seed> found = seedService.getSeedById(1L);
        assertTrue(found.isPresent());
        assertEquals(seed.getId(), found.get().getId());
    }

    @Test
    void updateSeed() {
        when(seedRepository.findById(1L)).thenReturn(Optional.of(seed));
        when(seedRepository.save(any(Seed.class))).thenReturn(seed);

        Seed updated = seedService.updateSeed(1L, seed);
        assertEquals(seed.getId(), updated.getId());
        verify(seedRepository).save(any(Seed.class));
    }

    @Test
    void deleteSeed() {
        doNothing().when(seedRepository).deleteById(1L);
        seedService.deleteSeed(1L);
        verify(seedRepository).deleteById(1L);
    }

    @Test
    void getSeedsByUser() {
        List<Seed> seeds = Arrays.asList(seed);
        when(seedRepository.findByUserId(1L)).thenReturn(seeds);
        List<Seed> found = seedService.getSeedsByUser(1L);
        assertEquals(1, found.size());
    }
}