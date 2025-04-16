package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.Recipe;
import com.tfg.tfg.Repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceTest {
    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    private Recipe recipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipe = Recipe.builder()
                .id(1L)
                .object_name("Diamond Pickaxe")
                .imageUrl("http://example.com/diamond_pickaxe.png")
                .materials(new ArrayList<>())
                .build();
    }

    @Test
    void createRecipe() {
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);
        Recipe created = recipeService.createRecipe(recipe);
        assertEquals(recipe.getObject_name(), created.getObject_name());
        verify(recipeRepository).save(any(Recipe.class));
    }

    @Test
    void findRecipeById() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
        Recipe found = recipeService.findRecipeById(1L);
        assertEquals(recipe.getObject_name(), found.getObject_name());
    }

    @Test
    void findAllRecipes() {
        List<Recipe> recipes = Arrays.asList(recipe);
        when(recipeRepository.findAll()).thenReturn(recipes);
        List<Recipe> found = recipeService.findAllRecipes();
        assertEquals(1, found.size());
    }

    @Test
    void updateRecipe() {
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        Recipe updated = recipeService.updateRecipe(1L, recipe);
        assertEquals(recipe.getObject_name(), updated.getObject_name());
        verify(recipeRepository).save(any(Recipe.class));
    }
}