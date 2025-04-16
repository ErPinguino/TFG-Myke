package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.Biome;
import com.tfg.tfg.Entities.ResourceLocation;
import com.tfg.tfg.Entities.Resources;
import com.tfg.tfg.Entities.Seed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResourceLocationOutPutDTOTest {
    private ResourceLocationOutPutDTO resourceLocationOutPutDTO;
    private final Long ID = 1L;
    private final int COORDINATE_X = 100;
    private final int COORDINATE_Y = 64;
    private final int COORDINATE_Z = -200;
    private final Long SEED_ID = 1L;
    private final Long RESOURCE_ID = 2L;
    private final Long BIOME_ID = 3L;

    @BeforeEach
    void setUp() {
        resourceLocationOutPutDTO = ResourceLocationOutPutDTO.builder()
                .id(ID)
                .coordinateX(COORDINATE_X)
                .coordinateY(COORDINATE_Y)
                .coordinateZ(COORDINATE_Z)
                .seedId(SEED_ID)
                .resourceId(RESOURCE_ID)
                .biomeId(BIOME_ID)
                .build();
    }

    @Test
    void fromEntity() {
        Seed seed = Seed.builder().id(SEED_ID).build();
        Resources resource = Resources.builder().id(RESOURCE_ID).build();
        Biome biome = Biome.builder().id(BIOME_ID).build();

        ResourceLocation location = ResourceLocation.builder()
                .id(ID)
                .coordinateX(COORDINATE_X)
                .coordinateY(COORDINATE_Y)
                .coordinateZ(COORDINATE_Z)
                .seed(seed)
                .resource(resource)
                .biome(biome)
                .build();

        ResourceLocationOutPutDTO dto = ResourceLocationOutPutDTO.fromEntity(location);
        assertEquals(ID, dto.getId());
        assertEquals(COORDINATE_X, dto.getCoordinateX());
        assertEquals(COORDINATE_Y, dto.getCoordinateY());
        assertEquals(COORDINATE_Z, dto.getCoordinateZ());
        assertEquals(SEED_ID, dto.getSeedId());
        assertEquals(RESOURCE_ID, dto.getResourceId());
        assertEquals(BIOME_ID, dto.getBiomeId());
    }

    @Test
    void getId() {
        assertEquals(ID, resourceLocationOutPutDTO.getId());
    }

    @Test
    void getCoordinateX() {
        assertEquals(COORDINATE_X, resourceLocationOutPutDTO.getCoordinateX());
    }

    @Test
    void getCoordinateY() {
        assertEquals(COORDINATE_Y, resourceLocationOutPutDTO.getCoordinateY());
    }

    @Test
    void getCoordinateZ() {
        assertEquals(COORDINATE_Z, resourceLocationOutPutDTO.getCoordinateZ());
    }

    @Test
    void getSeedId() {
        assertEquals(SEED_ID, resourceLocationOutPutDTO.getSeedId());
    }

    @Test
    void getResourceId() {
        assertEquals(RESOURCE_ID, resourceLocationOutPutDTO.getResourceId());
    }

    @Test
    void getBiomeId() {
        assertEquals(BIOME_ID, resourceLocationOutPutDTO.getBiomeId());
    }

    @Test
    void setId() {
        Long newId = 4L;
        resourceLocationOutPutDTO.setId(newId);
        assertEquals(newId, resourceLocationOutPutDTO.getId());
    }

    @Test
    void setCoordinateX() {
        int newX = 150;
        resourceLocationOutPutDTO.setCoordinateX(newX);
        assertEquals(newX, resourceLocationOutPutDTO.getCoordinateX());
    }

    @Test
    void setCoordinateY() {
        int newY = 70;
        resourceLocationOutPutDTO.setCoordinateY(newY);
        assertEquals(newY, resourceLocationOutPutDTO.getCoordinateY());
    }

    @Test
    void setCoordinateZ() {
        int newZ = -250;
        resourceLocationOutPutDTO.setCoordinateZ(newZ);
        assertEquals(newZ, resourceLocationOutPutDTO.getCoordinateZ());
    }

    @Test
    void setSeedId() {
        Long newSeedId = 4L;
        resourceLocationOutPutDTO.setSeedId(newSeedId);
        assertEquals(newSeedId, resourceLocationOutPutDTO.getSeedId());
    }

    @Test
    void setResourceId() {
        Long newResourceId = 5L;
        resourceLocationOutPutDTO.setResourceId(newResourceId);
        assertEquals(newResourceId, resourceLocationOutPutDTO.getResourceId());
    }

    @Test
    void setBiomeId() {
        Long newBiomeId = 6L;
        resourceLocationOutPutDTO.setBiomeId(newBiomeId);
        assertEquals(newBiomeId, resourceLocationOutPutDTO.getBiomeId());
    }

    @Test
    void testEquals() {
        ResourceLocationOutPutDTO dto1 = ResourceLocationOutPutDTO.builder()
                .id(ID)
                .coordinateX(COORDINATE_X)
                .coordinateY(COORDINATE_Y)
                .coordinateZ(COORDINATE_Z)
                .seedId(SEED_ID)
                .resourceId(RESOURCE_ID)
                .biomeId(BIOME_ID)
                .build();
        ResourceLocationOutPutDTO dto2 = ResourceLocationOutPutDTO.builder()
                .id(ID)
                .coordinateX(COORDINATE_X)
                .coordinateY(COORDINATE_Y)
                .coordinateZ(COORDINATE_Z)
                .seedId(SEED_ID)
                .resourceId(RESOURCE_ID)
                .biomeId(BIOME_ID)
                .build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        ResourceLocationOutPutDTO dto1 = ResourceLocationOutPutDTO.builder()
                .id(ID)
                .coordinateX(COORDINATE_X)
                .coordinateY(COORDINATE_Y)
                .coordinateZ(COORDINATE_Z)
                .seedId(SEED_ID)
                .resourceId(RESOURCE_ID)
                .biomeId(BIOME_ID)
                .build();
        ResourceLocationOutPutDTO dto2 = ResourceLocationOutPutDTO.builder()
                .id(ID)
                .coordinateX(COORDINATE_X)
                .coordinateY(COORDINATE_Y)
                .coordinateZ(COORDINATE_Z)
                .seedId(SEED_ID)
                .resourceId(RESOURCE_ID)
                .biomeId(BIOME_ID)
                .build();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void builder() {
        ResourceLocationOutPutDTO dto = ResourceLocationOutPutDTO.builder()
                .id(ID)
                .coordinateX(COORDINATE_X)
                .coordinateY(COORDINATE_Y)
                .coordinateZ(COORDINATE_Z)
                .seedId(SEED_ID)
                .resourceId(RESOURCE_ID)
                .biomeId(BIOME_ID)
                .build();
        assertNotNull(dto);
        assertEquals(ID, dto.getId());
        assertEquals(COORDINATE_X, dto.getCoordinateX());
        assertEquals(COORDINATE_Y, dto.getCoordinateY());
        assertEquals(COORDINATE_Z, dto.getCoordinateZ());
        assertEquals(SEED_ID, dto.getSeedId());
        assertEquals(RESOURCE_ID, dto.getResourceId());
        assertEquals(BIOME_ID, dto.getBiomeId());
    }
}