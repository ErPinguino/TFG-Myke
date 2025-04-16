package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.RecipeInPutDTO;
import com.tfg.tfg.DTOs.RecipeOutPutDTO;
import com.tfg.tfg.Entities.Recipe;
import com.tfg.tfg.Services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    private Recipe recipe;
    private RecipeInPutDTO inputDTO;
    private RecipeOutPutDTO outputDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        recipe = Recipe.builder()
            .id(1L)
            .object_name("Test Recipe")
            .imageUrl("http://test.com/image.jpg")
            .build();

        inputDTO = RecipeInPutDTO.builder()
            .object_name("Test Recipe")
            .imageUrl("http://test.com/image.jpg")
            .build();

        outputDTO = RecipeOutPutDTO.builder()
            .id(1L)
            .object_name("Test Recipe")
            .imageUrl("http://test.com/image.jpg")
            .build();
    }

    @Test
    void createRecipe_ShouldReturnCreatedRecipe() {
        // Given
        when(recipeService.createRecipe(any(Recipe.class)))
            .thenReturn(recipe);

        // When
        ResponseEntity<RecipeOutPutDTO> response =
            recipeController.createRecipe(inputDTO);

        // Then
        verify(recipeService).createRecipe(any(Recipe.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test Recipe", response.getBody().getObject_name());
        assertEquals("http://test.com/image.jpg", response.getBody().getImageUrl());
    }

    @Test
    void getRecipeById_ShouldReturnRecipe() {
        // Given
        when(recipeService.findRecipeById(1L))
            .thenReturn(recipe);

        // When
        ResponseEntity<RecipeOutPutDTO> response =
            recipeController.getRecipeById(1L);

        // Then
        verify(recipeService).findRecipeById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test Recipe", response.getBody().getObject_name());
        assertEquals("http://test.com/image.jpg", response.getBody().getImageUrl());
    }

    @Test
    void getAllRecipes_ShouldReturnListOfRecipes() {
        // Given
        when(recipeService.findAllRecipes())
            .thenReturn(Arrays.asList(recipe));

        // When
        ResponseEntity<List<RecipeOutPutDTO>> response =
            recipeController.getAllRecipes();

        // Then
        verify(recipeService).findAllRecipes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(1L, response.getBody().get(0).getId());
        assertEquals("Test Recipe", response.getBody().get(0).getObject_name());
        assertEquals("http://test.com/image.jpg", response.getBody().get(0).getImageUrl());
    }

    @Test
    void updateRecipe_ShouldReturnUpdatedRecipe() {
        // Given
        when(recipeService.updateRecipe(eq(1L), any(Recipe.class)))
            .thenReturn(recipe);

        // When
        ResponseEntity<RecipeOutPutDTO> response =
            recipeController.updateRecipe(1L, inputDTO);

        // Then
        verify(recipeService).updateRecipe(eq(1L), any(Recipe.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test Recipe", response.getBody().getObject_name());
        assertEquals("http://test.com/image.jpg", response.getBody().getImageUrl());
    }
}