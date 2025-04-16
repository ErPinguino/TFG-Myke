package com.tfg.tfg.Controller;


import com.tfg.tfg.DTOs.MaterialRecipeInPutDTO;
import com.tfg.tfg.DTOs.MaterialRecipeOutPutDTO;
import com.tfg.tfg.Entities.MaterialRecipe;
import com.tfg.tfg.Entities.Recipe;
import com.tfg.tfg.Entities.Resources;
import com.tfg.tfg.Services.MaterialRecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class MaterialRecipeControllerTest {

    @Mock
    private MaterialRecipeService materialRecipeService;

    @InjectMocks
    private MaterialRecipeController materialRecipeController;

    private MaterialRecipe materialRecipe;
    private MaterialRecipeInPutDTO inputDTO;
    private MaterialRecipeOutPutDTO outputDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Recipe recipe = Recipe.builder()
            .id(1L)
            .build();

        Resources resource = Resources.builder()
            .id(1L)
            .build();

        materialRecipe = MaterialRecipe.builder()
            .id(1L)
            .recipe(recipe)
            .resource(resource)
            .build();

        inputDTO = MaterialRecipeInPutDTO.builder()
            .recipeId(1L)
            .resourceId(1L)
            .build();

        outputDTO = MaterialRecipeOutPutDTO.builder()
            .id(1L)
            .recipeId(1L)
            .resourceId(1L)
            .build();
    }

    @Test
    void createMaterialRecipe_ShouldReturnCreatedMaterialRecipe() {
        // Given
        when(materialRecipeService.createMaterialRecipe(any(MaterialRecipe.class)))
            .thenReturn(materialRecipe);

        // When
        ResponseEntity<MaterialRecipeOutPutDTO> response =
            materialRecipeController.createMaterialRecipe(inputDTO);

        // Then
        verify(materialRecipeService).createMaterialRecipe(any(MaterialRecipe.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals(1L, response.getBody().getRecipeId());
        assertEquals(1L, response.getBody().getResourceId());
    }

    @Test
    void getAllMaterialRecipes_ShouldReturnListOfMaterialRecipes() {
        // Given
        when(materialRecipeService.getAllMaterialRecipes())
            .thenReturn(Arrays.asList(materialRecipe));

        // When
        ResponseEntity<List<MaterialRecipeOutPutDTO>> response =
            materialRecipeController.getAllMaterialRecipes();

        // Then
        verify(materialRecipeService).getAllMaterialRecipes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(1L, response.getBody().get(0).getId());
    }

    @Test
    void getMaterialRecipeById_WhenExists_ShouldReturnMaterialRecipe() {
        // Given
        when(materialRecipeService.getMaterialRecipeById(1L))
            .thenReturn(Optional.of(materialRecipe));

        // When
        ResponseEntity<MaterialRecipeOutPutDTO> response =
            materialRecipeController.getMaterialRecipeById(1L);

        // Then
        verify(materialRecipeService).getMaterialRecipeById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void getMaterialRecipeById_WhenNotExists_ShouldReturnNotFound() {
        // Given
        when(materialRecipeService.getMaterialRecipeById(1L))
            .thenReturn(Optional.empty());

        // When
        ResponseEntity<MaterialRecipeOutPutDTO> response =
            materialRecipeController.getMaterialRecipeById(1L);

        // Then
        verify(materialRecipeService).getMaterialRecipeById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateMaterialRecipe_ShouldReturnUpdatedMaterialRecipe() {
        // Given
        when(materialRecipeService.updateMaterialRecipe(eq(1L), any(MaterialRecipe.class)))
            .thenReturn(materialRecipe);

        // When
        ResponseEntity<MaterialRecipeOutPutDTO> response =
            materialRecipeController.updateMaterialRecipe(1L, inputDTO);

        // Then
        verify(materialRecipeService).updateMaterialRecipe(eq(1L), any(MaterialRecipe.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void deleteMaterialRecipe_ShouldReturnNoContent() {
        // Given
        doNothing().when(materialRecipeService).deleteMaterialRecipe(1L);

        // When
        ResponseEntity<Void> response = materialRecipeController.deleteMaterialRecipe(1L);

        // Then
        verify(materialRecipeService).deleteMaterialRecipe(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}