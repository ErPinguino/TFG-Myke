package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.MaterialRecipe;
import com.tfg.tfg.Entities.Recipe;
import com.tfg.tfg.Entities.Resources;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaterialRecipeInPutDTOTest {
    private MaterialRecipeInPutDTO materialRecipeInPutDTO;
    private final Long RECIPE_ID = 1L;
    private final Long RESOURCE_ID = 2L;

    @BeforeEach
    void setUp() {
        materialRecipeInPutDTO = MaterialRecipeInPutDTO.builder()
                .recipeId(RECIPE_ID)
                .resourceId(RESOURCE_ID)
                .build();
    }

    @Test
    void toEntity() {
        MaterialRecipe entity = materialRecipeInPutDTO.toEntity();
        assertEquals(RECIPE_ID, entity.getRecipe().getId());
        assertEquals(RESOURCE_ID, entity.getResource().getId());
    }

    @Test
    void getRecipeId() {
        assertEquals(RECIPE_ID, materialRecipeInPutDTO.getRecipeId());
    }

    @Test
    void getResourceId() {
        assertEquals(RESOURCE_ID, materialRecipeInPutDTO.getResourceId());
    }

    @Test
    void setRecipeId() {
        Long newRecipeId = 3L;
        materialRecipeInPutDTO.setRecipeId(newRecipeId);
        assertEquals(newRecipeId, materialRecipeInPutDTO.getRecipeId());
    }

    @Test
    void setResourceId() {
        Long newResourceId = 4L;
        materialRecipeInPutDTO.setResourceId(newResourceId);
        assertEquals(newResourceId, materialRecipeInPutDTO.getResourceId());
    }

    @Test
    void testEquals() {
        MaterialRecipeInPutDTO dto1 = MaterialRecipeInPutDTO.builder()
                .recipeId(RECIPE_ID)
                .resourceId(RESOURCE_ID)
                .build();
        MaterialRecipeInPutDTO dto2 = MaterialRecipeInPutDTO.builder()
                .recipeId(RECIPE_ID)
                .resourceId(RESOURCE_ID)
                .build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        MaterialRecipeInPutDTO dto1 = MaterialRecipeInPutDTO.builder()
                .recipeId(RECIPE_ID)
                .resourceId(RESOURCE_ID)
                .build();
        MaterialRecipeInPutDTO dto2 = MaterialRecipeInPutDTO.builder()
                .recipeId(RECIPE_ID)
                .resourceId(RESOURCE_ID)
                .build();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void builder() {
        MaterialRecipeInPutDTO dto = MaterialRecipeInPutDTO.builder()
                .recipeId(RECIPE_ID)
                .resourceId(RESOURCE_ID)
                .build();
        assertNotNull(dto);
        assertEquals(RECIPE_ID, dto.getRecipeId());
        assertEquals(RESOURCE_ID, dto.getResourceId());
    }
}