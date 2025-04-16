package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.Seed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SeedInPutDTOTest {
    private SeedInPutDTO seedInPutDTO;
    private final String SEED_VALUE = "12345";
    private List<ResourceLocationOutPutDTO> resourceLocations;

    @BeforeEach
    void setUp() {
        // Given
        resourceLocations = new ArrayList<>();
        seedInPutDTO = SeedInPutDTO.builder()
                .seed_value(SEED_VALUE)
                .resourceLocations(resourceLocations)
                .build();
    }

    @Test
    void fromEntity() {
        // Given
        Seed seed = Seed.builder()
                .seed_value(SEED_VALUE)
                .resourceLocationList(new ArrayList<>())
                .build();

        // When
        SeedInPutDTO result = SeedInPutDTO.fromEntity(seed);

        // Then
        assertEquals(SEED_VALUE, result.getSeed_value());
        assertNotNull(result.getResourceLocations());
    }

    @Test
    void getSeed_value() {
        // When
        String result = seedInPutDTO.getSeed_value();

        // Then
        assertEquals(SEED_VALUE, result);
    }

    @Test
    void getResourceLocations() {
        // When
        List<ResourceLocationOutPutDTO> result = seedInPutDTO.getResourceLocations();

        // Then
        assertEquals(resourceLocations, result);
    }

    @Test
    void setSeed_value() {
        // Given
        String newSeedValue = "67890";

        // When
        seedInPutDTO.setSeed_value(newSeedValue);

        // Then
        assertEquals(newSeedValue, seedInPutDTO.getSeed_value());
    }

    @Test
    void setResourceLocations() {
        // Given
        List<ResourceLocationOutPutDTO> newLocations = new ArrayList<>();

        // When
        seedInPutDTO.setResourceLocations(newLocations);

        // Then
        assertEquals(newLocations, seedInPutDTO.getResourceLocations());
    }

    @Test
    void testEquals() {
        // Given
        SeedInPutDTO dto1 = SeedInPutDTO.builder()
                .seed_value(SEED_VALUE)
                .resourceLocations(resourceLocations)
                .build();
        SeedInPutDTO dto2 = SeedInPutDTO.builder()
                .seed_value(SEED_VALUE)
                .resourceLocations(resourceLocations)
                .build();

        // When-Then
        assertEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        // Given
        SeedInPutDTO dto1 = SeedInPutDTO.builder()
                .seed_value(SEED_VALUE)
                .resourceLocations(resourceLocations)
                .build();
        SeedInPutDTO dto2 = SeedInPutDTO.builder()
                .seed_value(SEED_VALUE)
                .resourceLocations(resourceLocations)
                .build();

        // When-Then
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void builder() {
        // When
        SeedInPutDTO dto = SeedInPutDTO.builder()
                .seed_value(SEED_VALUE)
                .resourceLocations(resourceLocations)
                .build();

        // Then
        assertNotNull(dto);
        assertEquals(SEED_VALUE, dto.getSeed_value());
        assertEquals(resourceLocations, dto.getResourceLocations());
    }
}