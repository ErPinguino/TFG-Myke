package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.Seed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SeedOutPutDTOTest {
    private SeedOutPutDTO seedOutPutDTO;
    private final Long ID = 1L;
    private final String SEED_VALUE = "12345";
    private List<ResourceLocationOutPutDTO> resourceLocations;

    @BeforeEach
    void setUp() {
        // Given
        resourceLocations = new ArrayList<>();
        seedOutPutDTO = SeedOutPutDTO.builder()
                .id(ID)
                .seed_value(SEED_VALUE)
                .resourceLocations(resourceLocations)
                .build();
    }

    @Test
    void fromEntity() {
        // Given
        Seed seed = Seed.builder()
                .id(ID)
                .seed_value(SEED_VALUE)
                .resourceLocationList(new ArrayList<>())
                .build();

        // When
        SeedOutPutDTO result = SeedOutPutDTO.fromEntity(seed);

        // Then
        assertEquals(ID, result.getId());
        assertEquals(SEED_VALUE, result.getSeed_value());
        assertNotNull(result.getResourceLocations());
    }

    @Test
    void getId() {
        // When
        Long result = seedOutPutDTO.getId();

        // Then
        assertEquals(ID, result);
    }

    @Test
    void getSeed_value() {
        // When
        String result = seedOutPutDTO.getSeed_value();

        // Then
        assertEquals(SEED_VALUE, result);
    }

    @Test
    void getResourceLocations() {
        // When
        List<ResourceLocationOutPutDTO> result = seedOutPutDTO.getResourceLocations();

        // Then
        assertEquals(resourceLocations, result);
    }

    @Test
    void setId() {
        // Given
        Long newId = 2L;

        // When
        seedOutPutDTO.setId(newId);

        // Then
        assertEquals(newId, seedOutPutDTO.getId());
    }

    @Test
    void setSeed_value() {
        // Given
        String newSeedValue = "67890";

        // When
        seedOutPutDTO.setSeed_value(newSeedValue);

        // Then
        assertEquals(newSeedValue, seedOutPutDTO.getSeed_value());
    }

    @Test
    void setResourceLocations() {
        // Given
        List<ResourceLocationOutPutDTO> newLocations = new ArrayList<>();

        // When
        seedOutPutDTO.setResourceLocations(newLocations);

        // Then
        assertEquals(newLocations, seedOutPutDTO.getResourceLocations());
    }

    @Test
    void testEquals() {
        // Given
        SeedOutPutDTO dto1 = SeedOutPutDTO.builder()
                .id(ID)
                .seed_value(SEED_VALUE)
                .resourceLocations(resourceLocations)
                .build();
        SeedOutPutDTO dto2 = SeedOutPutDTO.builder()
                .id(ID)
                .seed_value(SEED_VALUE)
                .resourceLocations(resourceLocations)
                .build();

        // When-Then
        assertEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        // Given
        SeedOutPutDTO dto1 = SeedOutPutDTO.builder()
                .id(ID)
                .seed_value(SEED_VALUE)
                .resourceLocations(resourceLocations)
                .build();
        SeedOutPutDTO dto2 = SeedOutPutDTO.builder()
                .id(ID)
                .seed_value(SEED_VALUE)
                .resourceLocations(resourceLocations)
                .build();

        // When-Then
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void builder() {
        // When
        SeedOutPutDTO dto = SeedOutPutDTO.builder()
                .id(ID)
                .seed_value(SEED_VALUE)
                .resourceLocations(resourceLocations)
                .build();

        // Then
        assertNotNull(dto);
        assertEquals(ID, dto.getId());
        assertEquals(SEED_VALUE, dto.getSeed_value());
        assertEquals(resourceLocations, dto.getResourceLocations());
    }
}