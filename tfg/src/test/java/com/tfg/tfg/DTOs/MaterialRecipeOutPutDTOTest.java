package com.tfg.tfg.DTOs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaterialRecipeOutPutDTOTest {
    private MaterialRecipeOutPutDTO materialRecipeOutPutDTO;
    private final Long ID = 1L;
    private final Long RECIPE_ID = 2L;
    private final Long RESOURCE_ID = 3L;

    @BeforeEach
    void setUp() {
        materialRecipeOutPutDTO = MaterialRecipeOutPutDTO.builder()
                .id(ID)
                .recipeId(RECIPE_ID)
                .resourceId(RESOURCE_ID)
                .build();
    }

    @Test
    void fromEntity() {
        MaterialRecipeOutPutDTO dto = MaterialRecipeOutPutDTO.fromEntity(ID, RECIPE_ID, RESOURCE_ID);
        assertEquals(ID, dto.getId());
        assertEquals(RECIPE_ID, dto.getRecipeId());
        assertEquals(RESOURCE_ID, dto.getResourceId());
    }

    @Test
    void getId() {
        assertEquals(ID, materialRecipeOutPutDTO.getId());
    }

    @Test
    void getRecipeId() {
        assertEquals(RECIPE_ID, materialRecipeOutPutDTO.getRecipeId());
    }

    @Test
    void getResourceId() {
        assertEquals(RESOURCE_ID, materialRecipeOutPutDTO.getResourceId());
    }

    @Test
    void setId() {
        Long newId = 4L;
        materialRecipeOutPutDTO.setId(newId);
        assertEquals(newId, materialRecipeOutPutDTO.getId());
    }

    @Test
    void setRecipeId() {
        Long newRecipeId = 5L;
        materialRecipeOutPutDTO.setRecipeId(newRecipeId);
        assertEquals(newRecipeId, materialRecipeOutPutDTO.getRecipeId());
    }

    @Test
    void setResourceId() {
        Long newResourceId = 6L;
        materialRecipeOutPutDTO.setResourceId(newResourceId);
        assertEquals(newResourceId, materialRecipeOutPutDTO.getResourceId());
    }

    @Test
    void testEquals() {
        MaterialRecipeOutPutDTO dto1 = MaterialRecipeOutPutDTO.builder()
                .id(ID)
                .recipeId(RECIPE_ID)
                .resourceId(RESOURCE_ID)
                .build();
        MaterialRecipeOutPutDTO dto2 = MaterialRecipeOutPutDTO.builder()
                .id(ID)
                .recipeId(RECIPE_ID)
                .resourceId(RESOURCE_ID)
                .build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        MaterialRecipeOutPutDTO dto1 = MaterialRecipeOutPutDTO.builder()
                .id(ID)
                .recipeId(RECIPE_ID)
                .resourceId(RESOURCE_ID)
                .build();
        MaterialRecipeOutPutDTO dto2 = MaterialRecipeOutPutDTO.builder()
                .id(ID)
                .recipeId(RECIPE_ID)
                .resourceId(RESOURCE_ID)
                .build();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void builder() {
        MaterialRecipeOutPutDTO dto = MaterialRecipeOutPutDTO.builder()
                .id(ID)
                .recipeId(RECIPE_ID)
                .resourceId(RESOURCE_ID)
                .build();
        assertNotNull(dto);
        assertEquals(ID, dto.getId());
        assertEquals(RECIPE_ID, dto.getRecipeId());
        assertEquals(RESOURCE_ID, dto.getResourceId());
    }
}