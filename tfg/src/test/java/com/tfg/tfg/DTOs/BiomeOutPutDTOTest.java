package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.Biome;
import com.tfg.tfg.Entities.ResourceLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BiomeOutPutDTOTest {
    private BiomeOutPutDTO biomeOutPutDTO;
    private final Long BIOME_ID = 1L;
    private final String BIOME_NAME = "Desert";
    private List<ResourceLocationOutPutDTO> resourceLocations;

    @BeforeEach
    void setUp() {
        resourceLocations = new ArrayList<>();
        biomeOutPutDTO = BiomeOutPutDTO.builder()
                .id(BIOME_ID)
                .name(BIOME_NAME)
                .resourceLocations(resourceLocations)
                .build();
    }

    @Test
    void fromEntity() {
        Biome biome = Biome.builder()
                .id(BIOME_ID)
                .name(BIOME_NAME)
                .locations(new ArrayList<>())
                .build();

        BiomeOutPutDTO dto = BiomeOutPutDTO.fromEntity(biome);
        assertEquals(BIOME_ID, dto.getId());
        assertEquals(BIOME_NAME, dto.getName());
        assertNotNull(dto.getResourceLocations());
    }

    @Test
    void getId() {
        assertEquals(BIOME_ID, biomeOutPutDTO.getId());
    }

    @Test
    void getName() {
        assertEquals(BIOME_NAME, biomeOutPutDTO.getName());
    }

    @Test
    void getResourceLocations() {
        assertNotNull(biomeOutPutDTO.getResourceLocations());
    }

    @Test
    void setId() {
        Long newId = 2L;
        biomeOutPutDTO.setId(newId);
        assertEquals(newId, biomeOutPutDTO.getId());
    }

    @Test
    void setName() {
        String newName = "Forest";
        biomeOutPutDTO.setName(newName);
        assertEquals(newName, biomeOutPutDTO.getName());
    }

    @Test
    void setResourceLocations() {
        List<ResourceLocationOutPutDTO> newLocations = new ArrayList<>();
        biomeOutPutDTO.setResourceLocations(newLocations);
        assertEquals(newLocations, biomeOutPutDTO.getResourceLocations());
    }

    @Test
    void testEquals() {
        BiomeOutPutDTO dto1 = BiomeOutPutDTO.builder()
                .id(BIOME_ID)
                .name(BIOME_NAME)
                .resourceLocations(resourceLocations)
                .build();
        BiomeOutPutDTO dto2 = BiomeOutPutDTO.builder()
                .id(BIOME_ID)
                .name(BIOME_NAME)
                .resourceLocations(resourceLocations)
                .build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        BiomeOutPutDTO dto1 = BiomeOutPutDTO.builder()
                .id(BIOME_ID)
                .name(BIOME_NAME)
                .resourceLocations(resourceLocations)
                .build();
        BiomeOutPutDTO dto2 = BiomeOutPutDTO.builder()
                .id(BIOME_ID)
                .name(BIOME_NAME)
                .resourceLocations(resourceLocations)
                .build();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void builder() {
        BiomeOutPutDTO dto = BiomeOutPutDTO.builder()
                .id(BIOME_ID)
                .name(BIOME_NAME)
                .resourceLocations(resourceLocations)
                .build();
        assertNotNull(dto);
        assertEquals(BIOME_ID, dto.getId());
        assertEquals(BIOME_NAME, dto.getName());
        assertEquals(resourceLocations, dto.getResourceLocations());
    }
}