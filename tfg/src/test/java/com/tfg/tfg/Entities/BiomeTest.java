package com.tfg.tfg.Entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BiomeTest {

    @Test
    void givenBiomeAttributes_whenBuildingBiome_thenBiomeIsCreatedCorrectly() {
        // Given
        Long expectedId = 1L;
        String expectedName = "Forest";
        List<ResourceLocation> expectedLocations = new ArrayList<>();

        // When
        Biome biome = Biome.builder()
                .id(expectedId)
                .name(expectedName)
                .locations(expectedLocations)
                .build();

        // Then
        assertAll("Verificar propiedades del Biome",
                () -> assertEquals(expectedId, biome.getId(), "El ID no coincide"),
                () -> assertEquals(expectedName, biome.getName(), "El nombre no coincide"),
                () -> assertNotNull(biome.getLocations(), "La lista de locations no debería ser null"),
                () -> assertTrue(biome.getLocations().isEmpty(), "La lista de locations debería estar vacía")
        );
    }

    @Test
    void givenBiomeWithLocation_whenAddingLocation_thenRelationshipIsEstablished() {
        // Given
        Biome biome = Biome.builder()
                .id(1L)
                .name("Forest")
                .locations(new ArrayList<>())
                .build();

        ResourceLocation location = mock(ResourceLocation.class);
        when(location.getBiome()).thenReturn(biome);

        // When
        biome.getLocations().add(location);

        // Then
        assertAll("Verificar relación Biome-Location",
                () -> assertEquals(1, biome.getLocations().size()),
                () -> assertEquals(location, biome.getLocations().get(0)),
                () -> assertEquals(biome, location.getBiome()),
                () -> assertEquals(biome, location.getBiome())
        );
    }

    @Test
    void givenTwoBiomesWithSameAttributes_whenComparing_thenTheyAreEqual() {
        // Given
        Biome biome1 = Biome.builder()
                .id(1L)
                .name("Forest")
                .locations(new ArrayList<>())
                .build();

        Biome biome2 = Biome.builder()
                .id(1L)
                .name("Forest")
                .locations(new ArrayList<>())
                .build();

        // When - Then
        assertEquals(biome1, biome2);
        assertEquals(biome1.hashCode(), biome2.hashCode());
    }

    @Test
    void givenTwoDifferentBiomes_whenComparing_thenTheyAreNotEqual() {
        // Given
        Biome forestBiome = Biome.builder()
                .id(1L)
                .name("Forest")
                .locations(new ArrayList<>())
                .build();

        Biome desertBiome = Biome.builder()
                .id(2L)
                .name("Desert")
                .locations(new ArrayList<>())
                .build();

        // When - Then
        assertNotEquals(forestBiome, desertBiome);
        assertNotEquals(forestBiome.hashCode(), desertBiome.hashCode());
    }

    @Test
    void givenBiome_whenCallingToString_thenReturnsNonEmptyString() {
        // Given
        Biome biome = Biome.builder()
                .id(1L)
                .name("Forest")
                .locations(new ArrayList<>())
                .build();

        // When
        String biomeString = biome.toString();

        // Then
        assertNotNull(biomeString);
        assertFalse(biomeString.isEmpty());
    }

    @Test
    void givenEmptyBiome_whenSettingProperties_thenPropertiesAreSetCorrectly() {
        // Given
        Biome biome = new Biome();
        Long expectedId = 3L;
        String expectedName = "Tundra";
        List<ResourceLocation> expectedLocations = new ArrayList<>();

        // When
        biome.setId(expectedId);
        biome.setName(expectedName);
        biome.setLocations(expectedLocations);

        // Then
        assertAll("Verificar propiedades establecidas",
                () -> assertEquals(expectedId, biome.getId()),
                () -> assertEquals(expectedName, biome.getName()),
                () -> assertEquals(expectedLocations, biome.getLocations())
        );
    }
}