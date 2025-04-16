package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.MaterialRecipe;
import com.tfg.tfg.Entities.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RecipeOutPutDTOTest {
    private RecipeOutPutDTO recipeOutPutDTO;
    private final Long ID = 1L;
    private final String OBJECT_NAME = "Diamond Pickaxe";
    private final String IMAGE_URL = "http://example.com/diamond_pickaxe.png";
    private List<MaterialRecipe> materialRecipes;

    @BeforeEach
    void setUp() {
        materialRecipes = new ArrayList<>();
        recipeOutPutDTO = RecipeOutPutDTO.builder()
                .id(ID)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materialRecipes(materialRecipes)
                .build();
    }

    @Test
    void fromEntity() {
        Recipe recipe = Recipe.builder()
                .id(ID)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materials(materialRecipes)
                .build();

        RecipeOutPutDTO dto = RecipeOutPutDTO.fromEntity(recipe);
        assertEquals(ID, dto.getId());
        assertEquals(OBJECT_NAME, dto.getObject_name());
        assertEquals(IMAGE_URL, dto.getImageUrl());
        assertEquals(materialRecipes, dto.getMaterialRecipes());
    }

    @Test
    void getId() {
        assertEquals(ID, recipeOutPutDTO.getId());
    }

    @Test
    void getObject_name() {
        assertEquals(OBJECT_NAME, recipeOutPutDTO.getObject_name());
    }

    @Test
    void getImageUrl() {
        assertEquals(IMAGE_URL, recipeOutPutDTO.getImageUrl());
    }

    @Test
    void getMaterialRecipes() {
        assertEquals(materialRecipes, recipeOutPutDTO.getMaterialRecipes());
    }

    @Test
    void setId() {
        Long newId = 2L;
        recipeOutPutDTO.setId(newId);
        assertEquals(newId, recipeOutPutDTO.getId());
    }

    @Test
    void setObject_name() {
        String newObjectName = "Iron Pickaxe";
        recipeOutPutDTO.setObject_name(newObjectName);
        assertEquals(newObjectName, recipeOutPutDTO.getObject_name());
    }

    @Test
    void setImageUrl() {
        String newImageUrl = "http://example.com/iron_pickaxe.png";
        recipeOutPutDTO.setImageUrl(newImageUrl);
        assertEquals(newImageUrl, recipeOutPutDTO.getImageUrl());
    }

    @Test
    void setMaterialRecipes() {
        List<MaterialRecipe> newMaterialRecipes = new ArrayList<>();
        recipeOutPutDTO.setMaterialRecipes(newMaterialRecipes);
        assertEquals(newMaterialRecipes, recipeOutPutDTO.getMaterialRecipes());
    }

    @Test
    void testEquals() {
        RecipeOutPutDTO dto1 = RecipeOutPutDTO.builder()
                .id(ID)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materialRecipes(materialRecipes)
                .build();
        RecipeOutPutDTO dto2 = RecipeOutPutDTO.builder()
                .id(ID)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materialRecipes(materialRecipes)
                .build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        RecipeOutPutDTO dto1 = RecipeOutPutDTO.builder()
                .id(ID)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materialRecipes(materialRecipes)
                .build();
        RecipeOutPutDTO dto2 = RecipeOutPutDTO.builder()
                .id(ID)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materialRecipes(materialRecipes)
                .build();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void builder() {
        RecipeOutPutDTO dto = RecipeOutPutDTO.builder()
                .id(ID)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materialRecipes(materialRecipes)
                .build();
        assertNotNull(dto);
        assertEquals(ID, dto.getId());
        assertEquals(OBJECT_NAME, dto.getObject_name());
        assertEquals(IMAGE_URL, dto.getImageUrl());
        assertEquals(materialRecipes, dto.getMaterialRecipes());
    }
}