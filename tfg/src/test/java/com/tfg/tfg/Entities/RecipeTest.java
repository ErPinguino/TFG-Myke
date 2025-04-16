package com.tfg.tfg.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    @Mock
    private MaterialRecipe materialRecipe;

    private Recipe recipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ArrayList<MaterialRecipe> materials = new ArrayList<>();
        materials.add(materialRecipe);

        recipe = Recipe.builder()
                .id(1L)
                .object_name("Diamond Pickaxe")
                .imageUrl("https://example.com/diamond_pickaxe.png")
                .materials(materials)
                .build();
    }

    @Test
    void testRecipeProperties() {
        assertEquals(1L, recipe.getId());
        assertEquals("Diamond Pickaxe", recipe.getObject_name());
        assertEquals("https://example.com/diamond_pickaxe.png", recipe.getImageUrl());
        assertEquals(1, recipe.getMaterials().size());
    }
}