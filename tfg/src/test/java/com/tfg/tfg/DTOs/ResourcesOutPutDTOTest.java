package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.MaterialRecipe;
import com.tfg.tfg.Entities.Recipe;
import com.tfg.tfg.Entities.ResourceLocation;
import com.tfg.tfg.Entities.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ResourcesOutPutDTOTest {
    private ResourcesOutPutDTO resourcesOutPutDTO;
    private final Long ID = 1L;
    private final String NAME = "Diamond";
    private List<ResourceLocation> locations;
    private List<MaterialRecipeOutPutDTO> materialRecipe;

    @BeforeEach
    void setUp() {
        locations = new ArrayList<>();
        materialRecipe = new ArrayList<>();
        resourcesOutPutDTO = ResourcesOutPutDTO.builder()
                .id(ID)
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipe)
                .build();
    }

    @Test
    void fromEntity() {
        MaterialRecipe mr = MaterialRecipe.builder()
                .id(1L)
                .recipe(Recipe.builder().id(1L).build())
                .resource(Resources.builder().id(1L).build())
                .build();
        List<MaterialRecipe> materialRecipes = new ArrayList<>();
        materialRecipes.add(mr);

        Resources resource = Resources.builder()
                .id(ID)
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipes)
                .build();

        ResourcesOutPutDTO dto = ResourcesOutPutDTO.fromEntity(resource);
        assertEquals(ID, dto.getId());
        assertEquals(NAME, dto.getName());
        assertEquals(locations, dto.getLocations());
        assertNotNull(dto.getMaterialRecipe());
    }

    @Test
    void getId() {
        assertEquals(ID, resourcesOutPutDTO.getId());
    }

    @Test
    void getName() {
        assertEquals(NAME, resourcesOutPutDTO.getName());
    }

    @Test
    void getLocations() {
        assertEquals(locations, resourcesOutPutDTO.getLocations());
    }

    @Test
    void getMaterialRecipe() {
        assertEquals(materialRecipe, resourcesOutPutDTO.getMaterialRecipe());
    }

    @Test
    void setId() {
        Long newId = 2L;
        resourcesOutPutDTO.setId(newId);
        assertEquals(newId, resourcesOutPutDTO.getId());
    }

    @Test
    void setName() {
        String newName = "Iron";
        resourcesOutPutDTO.setName(newName);
        assertEquals(newName, resourcesOutPutDTO.getName());
    }

    @Test
    void setLocations() {
        List<ResourceLocation> newLocations = new ArrayList<>();
        resourcesOutPutDTO.setLocations(newLocations);
        assertEquals(newLocations, resourcesOutPutDTO.getLocations());
    }

    @Test
    void setMaterialRecipe() {
        List<MaterialRecipeOutPutDTO> newMaterialRecipe = new ArrayList<>();
        resourcesOutPutDTO.setMaterialRecipe(newMaterialRecipe);
        assertEquals(newMaterialRecipe, resourcesOutPutDTO.getMaterialRecipe());
    }

    @Test
    void testEquals() {
        ResourcesOutPutDTO dto1 = ResourcesOutPutDTO.builder()
                .id(ID)
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipe)
                .build();
        ResourcesOutPutDTO dto2 = ResourcesOutPutDTO.builder()
                .id(ID)
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipe)
                .build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        ResourcesOutPutDTO dto1 = ResourcesOutPutDTO.builder()
                .id(ID)
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipe)
                .build();
        ResourcesOutPutDTO dto2 = ResourcesOutPutDTO.builder()
                .id(ID)
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipe)
                .build();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void builder() {
        ResourcesOutPutDTO dto = ResourcesOutPutDTO.builder()
                .id(ID)
                .name(NAME)
                .locations(locations)
                .materialRecipe(materialRecipe)
                .build();
        assertNotNull(dto);
        assertEquals(ID, dto.getId());
        assertEquals(NAME, dto.getName());
        assertEquals(locations, dto.getLocations());
        assertEquals(materialRecipe, dto.getMaterialRecipe());
    }
}