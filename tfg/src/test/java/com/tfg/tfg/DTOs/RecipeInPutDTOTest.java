package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.MaterialRecipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RecipeInPutDTOTest {
    private RecipeInPutDTO recipeInPutDTO;
    private final String NAME = "Diamond Tool";
    private final String OBJECT_NAME = "Diamond Pickaxe";
    private final String IMAGE_URL = "http://example.com/diamond_pickaxe.png";
    private List<MaterialRecipe> materialRecipes;

    @BeforeEach
    void setUp() {
        materialRecipes = new ArrayList<>();
        recipeInPutDTO = RecipeInPutDTO.builder()
                .name(NAME)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materialRecipes(materialRecipes)
                .build();
    }

    @Test
    void toEntity() {
        RecipeInPutDTO entity = recipeInPutDTO.toEntity();
        assertEquals(NAME, entity.getName());
        assertEquals(OBJECT_NAME, entity.getObject_name());
        assertEquals(IMAGE_URL, entity.getImageUrl());
        assertEquals(materialRecipes, entity.getMaterialRecipes());
    }

    @Test
    void getName() {
        assertEquals(NAME, recipeInPutDTO.getName());
    }

    @Test
    void getObject_name() {
        assertEquals(OBJECT_NAME, recipeInPutDTO.getObject_name());
    }

    @Test
    void getImageUrl() {
        assertEquals(IMAGE_URL, recipeInPutDTO.getImageUrl());
    }

    @Test
    void getMaterialRecipes() {
        assertEquals(materialRecipes, recipeInPutDTO.getMaterialRecipes());
    }

    @Test
    void setName() {
        String newName = "Iron Tool";
        recipeInPutDTO.setName(newName);
        assertEquals(newName, recipeInPutDTO.getName());
    }

    @Test
    void setObject_name() {
        String newObjectName = "Iron Pickaxe";
        recipeInPutDTO.setObject_name(newObjectName);
        assertEquals(newObjectName, recipeInPutDTO.getObject_name());
    }

    @Test
    void setImageUrl() {
        String newImageUrl = "http://example.com/iron_pickaxe.png";
        recipeInPutDTO.setImageUrl(newImageUrl);
        assertEquals(newImageUrl, recipeInPutDTO.getImageUrl());
    }

    @Test
    void setMaterialRecipes() {
        List<MaterialRecipe> newMaterialRecipes = new ArrayList<>();
        recipeInPutDTO.setMaterialRecipes(newMaterialRecipes);
        assertEquals(newMaterialRecipes, recipeInPutDTO.getMaterialRecipes());
    }

    @Test
    void testEquals() {
        RecipeInPutDTO dto1 = RecipeInPutDTO.builder()
                .name(NAME)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materialRecipes(materialRecipes)
                .build();
        RecipeInPutDTO dto2 = RecipeInPutDTO.builder()
                .name(NAME)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materialRecipes(materialRecipes)
                .build();
        assertEquals(dto1, dto2);
    }

    @Test
    void testHashCode() {
        RecipeInPutDTO dto1 = RecipeInPutDTO.builder()
                .name(NAME)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materialRecipes(materialRecipes)
                .build();
        RecipeInPutDTO dto2 = RecipeInPutDTO.builder()
                .name(NAME)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materialRecipes(materialRecipes)
                .build();
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void builder() {
        RecipeInPutDTO dto = RecipeInPutDTO.builder()
                .name(NAME)
                .object_name(OBJECT_NAME)
                .imageUrl(IMAGE_URL)
                .materialRecipes(materialRecipes)
                .build();
        assertNotNull(dto);
        assertEquals(NAME, dto.getName());
        assertEquals(OBJECT_NAME, dto.getObject_name());
        assertEquals(IMAGE_URL, dto.getImageUrl());
        assertEquals(materialRecipes, dto.getMaterialRecipes());
    }
}