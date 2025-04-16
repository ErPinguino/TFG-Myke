package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.Biome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BiomeInPutDTOTest {
    private BiomeInPutDTO biomeInPutDTO;
    private final String BIOME_NAME = "Desert";

    @BeforeEach
    void setUp() {
        biomeInPutDTO = BiomeInPutDTO.builder()
                .name(BIOME_NAME)
                .build();
    }

    @Test
    void toEntity() {
        Biome biome = biomeInPutDTO.toEntity();
        assertEquals(BIOME_NAME, biome.getName());
    }

    @Test
    void getName() {
        assertEquals(BIOME_NAME, biomeInPutDTO.getName());
    }

    @Test
    void setName() {
        String newName = "Forest";
        biomeInPutDTO.setName(newName);
        assertEquals(newName, biomeInPutDTO.getName());
    }

    @Test
    void testEquals() {
        BiomeInPutDTO dto1 = BiomeInPutDTO.builder().name(BIOME_NAME).build();
        BiomeInPutDTO dto2 = BiomeInPutDTO.builder().name(BIOME_NAME).build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        BiomeInPutDTO dto1 = BiomeInPutDTO.builder().name(BIOME_NAME).build();
        BiomeInPutDTO dto2 = BiomeInPutDTO.builder().name(BIOME_NAME).build();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void builder() {
        BiomeInPutDTO dto = BiomeInPutDTO.builder()
                .name(BIOME_NAME)
                .build();
        assertNotNull(dto);
        assertEquals(BIOME_NAME, dto.getName());
    }
}