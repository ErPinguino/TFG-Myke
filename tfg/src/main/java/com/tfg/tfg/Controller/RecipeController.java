package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.RecipeInPutDTO;
import com.tfg.tfg.DTOs.RecipeOutPutDTO;
import com.tfg.tfg.Entities.Recipe;
import com.tfg.tfg.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/minecraftProject/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PostMapping
    public ResponseEntity<RecipeOutPutDTO> createRecipe(@RequestBody RecipeInPutDTO recipeInPutDTO) {
        Recipe recipe = Recipe.builder()
                .object_name(recipeInPutDTO.getObject_name())
                .imageUrl(recipeInPutDTO.getImageUrl())
                .build();

        Recipe createdRecipe = recipeService.createRecipe(recipe);
        return new ResponseEntity<>(RecipeOutPutDTO.fromEntity(createdRecipe), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeOutPutDTO> getRecipeById(@PathVariable Long id) {
        Recipe recipe = recipeService.findRecipeById(id);
        return ResponseEntity.ok(RecipeOutPutDTO.fromEntity(recipe));
    }

    @GetMapping
    public ResponseEntity<List<RecipeOutPutDTO>> getAllRecipes() {
        List<Recipe> recipes = recipeService.findAllRecipes();
        List<RecipeOutPutDTO> recipeOutPutDTOs = recipes.stream()
                .map(RecipeOutPutDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(recipeOutPutDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeOutPutDTO> updateRecipe(
            @PathVariable Long id,
            @RequestBody RecipeInPutDTO recipeInPutDTO) {

        Recipe updatedRecipe = Recipe.builder()
                .object_name(recipeInPutDTO.getObject_name())
                .imageUrl(recipeInPutDTO.getImageUrl())
                .build();

        Recipe savedRecipe = recipeService.updateRecipe(id, updatedRecipe);
        return ResponseEntity.ok(RecipeOutPutDTO.fromEntity(savedRecipe));
    }

    // Additional endpoints can be added here as needed
}