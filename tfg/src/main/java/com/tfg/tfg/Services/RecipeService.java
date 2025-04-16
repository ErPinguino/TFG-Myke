package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.Recipe;
import com.tfg.tfg.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    public List<Recipe> findAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe updateRecipe(Long id, Recipe updatedRecipe) {
        Recipe existingRecipe = findRecipeById(id);
        existingRecipe.setObject_name(updatedRecipe.getObject_name());
        existingRecipe.setImageUrl(updatedRecipe.getImageUrl());
        return recipeRepository.save(existingRecipe);
    }

}
