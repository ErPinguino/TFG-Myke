package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.ResourceLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResourceLocationInPutDTOTest {
    private ResourceLocationInPutDTO resourceLocationInPutDTO;
    private final int COORDINATE_X = 100;
    private final int COORDINATE_Y = 64;
    private final int COORDINATE_Z = -200;
    private final Long SEED_ID = 1L;
    private final Long RESOURCE_ID = 2L;
    private final Long BIOME_ID = 3L;

    @BeforeEach
    void setUp() {
        resourceLocationInPutDTO = ResourceLocationInPutDTO.builder()
                .coordinateX(COORDINATE_X)
                .coordinateY(COORDINATE_Y)
                .coordinateZ(COORDINATE_Z)
                .seedId(SEED_ID)
                .resourceId(RESOURCE_ID)
                .biomeId(BIOME_ID)
                .build();
    }

    @Test
    void toEntity() {
        ResourceLocation entity = resourceLocationInPutDTO.toEntity();
        assertEquals(COORDINATE_X, entity.getCoordinateX());
        assertEquals(COORDINATE_Y, entity.getCoordinateY());
        assertEquals(COORDINATE_Z, entity.getCoordinateZ());
    }

    @Test
    void getCoordinateX() {
        assertEquals(COORDINATE_X, resourceLocationInPutDTO.getCoordinateX());
    }

    @Test
    void getCoordinateY() {
        assertEquals(COORDINATE_Y, resourceLocationInPutDTO.getCoordinateY());
    }

    @Test
    void getCoordinateZ() {
        assertEquals(COORDINATE_Z, resourceLocationInPutDTO.getCoordinateZ());
    }

    @Test
    void getSeedId() {
        assertEquals(SEED_ID, resourceLocationInPutDTO.getSeedId());
    }

    @Test
    void getResourceId() {
        assertEquals(RESOURCE_ID, resourceLocationInPutDTO.getResourceId());
    }

    @Test
    void getBiomeId() {
        assertEquals(BIOME_ID, resourceLocationInPutDTO.getBiomeId());
    }

    @Test
    void setCoordinateX() {
        int newX = 150;
        resourceLocationInPutDTO.setCoordinateX(newX);
        assertEquals(newX, resourceLocationInPutDTO.getCoordinateX());
    }

    @Test
    void setCoordinateY() {
        int newY = 70;
        resourceLocationInPutDTO.setCoordinateY(newY);
        assertEquals(newY, resourceLocationInPutDTO.getCoordinateY());
    }

    @Test
    void setCoordinateZ() {
        int newZ = -250;
        resourceLocationInPutDTO.setCoordinateZ(newZ);
        assertEquals(newZ, resourceLocationInPutDTO.getCoordinateZ());
    }

    @Test
    void setSeedId() {
        Long newSeedId = 4L;
        resourceLocationInPutDTO.setSeedId(newSeedId);
        assertEquals(newSeedId, resourceLocationInPutDTO.getSeedId());
    }

    @Test
    void setResourceId() {
        Long newResourceId = 5L;
        resourceLocationInPutDTO.setResourceId(newResourceId);
        assertEquals(newResourceId, resourceLocationInPutDTO.getResourceId());
    }

    @Test
    void setBiomeId() {
        Long newBiomeId = 6L;
        resourceLocationInPutDTO.setBiomeId(newBiomeId);
        assertEquals(newBiomeId, resourceLocationInPutDTO.getBiomeId());
    }

    @Test
    void testEquals() {
        ResourceLocationInPutDTO dto1 = ResourceLocationInPutDTO.builder()
                .coordinateX(COORDINATE_X)
                .coordinateY(COORDINATE_Y)
                .coordinateZ(COORDINATE_Z)
                .seedId(SEED_ID)
                .resourceId(RESOURCE_ID)
                .biomeId(BIOME_ID)
                .build();
        ResourceLocationInPutDTO dto2 = ResourceLocationInPutDTO.builder()
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
        ResourceLocationInPutDTO dto1 = ResourceLocationInPutDTO.builder()
                .coordinateX(COORDINATE_X)
                .coordinateY(COORDINATE_Y)
                .coordinateZ(COORDINATE_Z)
                .seedId(SEED_ID)
                .resourceId(RESOURCE_ID)
                .biomeId(BIOME_ID)
                .build();
        ResourceLocationInPutDTO dto2 = ResourceLocationInPutDTO.builder()
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
        ResourceLocationInPutDTO dto = ResourceLocationInPutDTO.builder()
                .coordinateX(COORDINATE_X)
                .coordinateY(COORDINATE_Y)
                .coordinateZ(COORDINATE_Z)
                .seedId(SEED_ID)
                .resourceId(RESOURCE_ID)
                .biomeId(BIOME_ID)
                .build();
        assertNotNull(dto);
        assertEquals(COORDINATE_X, dto.getCoordinateX());
        assertEquals(COORDINATE_Y, dto.getCoordinateY());
        assertEquals(COORDINATE_Z, dto.getCoordinateZ());
        assertEquals(SEED_ID, dto.getSeedId());
        assertEquals(RESOURCE_ID, dto.getResourceId());
        assertEquals(BIOME_ID, dto.getBiomeId());
    }
}