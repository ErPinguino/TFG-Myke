package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.ResourceLocation;
import com.tfg.tfg.Entities.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ResourcesInPutDTOTest {
    private ResourcesInPutDTO resourcesInPutDTO;
    private final String NAME = "Diamond";
    private List<ResourceLocation> locations;
    private List<MaterialRecipeInPutDTO> materialRecipe;

    @BeforeEach
    void setUp() {
        locations = new ArrayList<>();
        materialRecipe = new ArrayList<>();
        resourcesInPutDTO = ResourcesInPutDTO.builder()
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipe)
                .build();
    }

    @Test
    void toEntity() {
        Resources entity = ResourcesInPutDTO.toEntity(resourcesInPutDTO);
        assertEquals(NAME, entity.getName());
        assertEquals(locations, entity.getLocations());
        assertNotNull(entity.getMaterialRecipe());
    }

    @Test
    void getName() {
        assertEquals(NAME, resourcesInPutDTO.getName());
    }

    @Test
    void getLocations() {
        assertEquals(locations, resourcesInPutDTO.getLocations());
    }

    @Test
    void getMaterialRecipe() {
        assertEquals(materialRecipe, resourcesInPutDTO.getMaterialRecipe());
    }

    @Test
    void setName() {
        String newName = "Iron";
        resourcesInPutDTO.setName(newName);
        assertEquals(newName, resourcesInPutDTO.getName());
    }

    @Test
    void setLocations() {
        List<ResourceLocation> newLocations = new ArrayList<>();
        resourcesInPutDTO.setLocations(newLocations);
        assertEquals(newLocations, resourcesInPutDTO.getLocations());
    }

    @Test
    void setMaterialRecipe() {
        List<MaterialRecipeInPutDTO> newMaterialRecipe = new ArrayList<>();
        resourcesInPutDTO.setMaterialRecipe(newMaterialRecipe);
        assertEquals(newMaterialRecipe, resourcesInPutDTO.getMaterialRecipe());
    }

    @Test
    void testEquals() {
        ResourcesInPutDTO dto1 = ResourcesInPutDTO.builder()
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipe)
                .build();
        ResourcesInPutDTO dto2 = ResourcesInPutDTO.builder()
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipe)
                .build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        ResourcesInPutDTO dto1 = ResourcesInPutDTO.builder()
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipe)
                .build();
        ResourcesInPutDTO dto2 = ResourcesInPutDTO.builder()
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipe)
                .build();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void builder() {
        ResourcesInPutDTO dto = ResourcesInPutDTO.builder()
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipe)
                .build();
        assertNotNull(dto);
        assertEquals(NAME, dto.getName());
        assertEquals(locations, dto.getLocations());
        assertEquals(materialRecipe, dto.getMaterialRecipe());
    }
}