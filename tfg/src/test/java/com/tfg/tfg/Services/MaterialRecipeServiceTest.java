package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.MaterialRecipe;
import com.tfg.tfg.Entities.Recipe;
import com.tfg.tfg.Entities.Resources;
import com.tfg.tfg.Repository.MaterialRecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MaterialRecipeServiceTest {
    @Mock
    private MaterialRecipeRepository materialRecipeRepository;

    @InjectMocks
    private MaterialRecipeService materialRecipeService;

    private MaterialRecipe materialRecipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        materialRecipe = MaterialRecipe.builder()
                .id(1L)
                .recipe(new Recipe())
                .resource(new Resources())
                .build();
    }

    @Test
    void createMaterialRecipe() {
        when(materialRecipeRepository.save(any(MaterialRecipe.class))).thenReturn(materialRecipe);
        MaterialRecipe created = materialRecipeService.createMaterialRecipe(materialRecipe);
        assertEquals(materialRecipe.getId(), created.getId());
        verify(materialRecipeRepository).save(any(MaterialRecipe.class));
    }

    @Test
    void getAllMaterialRecipes() {
        List<MaterialRecipe> recipes = Arrays.asList(materialRecipe);
        when(materialRecipeRepository.findAll()).thenReturn(recipes);
        List<MaterialRecipe> found = materialRecipeService.getAllMaterialRecipes();
        assertEquals(1, found.size());
        verify(materialRecipeRepository).findAll();
    }

    @Test
    void getMaterialRecipeById() {
        when(materialRecipeRepository.findById(1L)).thenReturn(Optional.of(materialRecipe));
        Optional<MaterialRecipe> found = materialRecipeService.getMaterialRecipeById(1L);
        assertTrue(found.isPresent());
        assertEquals(materialRecipe.getId(), found.get().getId());
    }

    @Test
    void updateMaterialRecipe() {
        when(materialRecipeRepository.findById(1L)).thenReturn(Optional.of(materialRecipe));
        when(materialRecipeRepository.save(any(MaterialRecipe.class))).thenReturn(materialRecipe);

        MaterialRecipe updated = materialRecipeService.updateMaterialRecipe(1L, materialRecipe);
        assertEquals(materialRecipe.getId(), updated.getId());
        verify(materialRecipeRepository).save(any(MaterialRecipe.class));
    }

    @Test
    void deleteMaterialRecipe() {
        doNothing().when(materialRecipeRepository).deleteById(1L);
        materialRecipeService.deleteMaterialRecipe(1L);
        verify(materialRecipeRepository).deleteById(1L);
    }
}