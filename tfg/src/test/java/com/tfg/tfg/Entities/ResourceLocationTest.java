package com.tfg.tfg.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ResourceLocationTest {
    @Mock
    private Seed seed;
    @Mock
    private Resources resource;
    @Mock
    private Biome biome;

    private ResourceLocation resourceLocation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resourceLocation = ResourceLocation.builder()
                .id(1L)
                .coordinateX(100)
                .coordinateY(64)
                .coordinateZ(-200)
                .seed(seed)
                .resource(resource)
                .biome(biome)
                .build();
    }

    @Test
    void testResourceLocationProperties() {
        assertEquals(1L, resourceLocation.getId());
        assertEquals(100, resourceLocation.getCoordinateX());
        assertEquals(64, resourceLocation.getCoordinateY());
        assertEquals(-200, resourceLocation.getCoordinateZ());
        assertEquals(seed, resourceLocation.getSeed());
        assertEquals(resource, resourceLocation.getResource());
        assertEquals(biome, resourceLocation.getBiome());
    }
}