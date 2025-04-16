package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.MaterialRecipe;
import com.tfg.tfg.Repository.MaterialRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialRecipeService {

    private final MaterialRecipeRepository materialRecipeRepository;

    @Autowired
    public MaterialRecipeService(MaterialRecipeRepository materialRecipeRepository) {
        this.materialRecipeRepository = materialRecipeRepository;
    }

    public MaterialRecipe createMaterialRecipe(MaterialRecipe materialRecipe) {
        return materialRecipeRepository.save(materialRecipe);
    }

    public List<MaterialRecipe> getAllMaterialRecipes() {
        return materialRecipeRepository.findAll();
    }

    public Optional<MaterialRecipe> getMaterialRecipeById(Long id) {
        return materialRecipeRepository.findById(id);
    }

    public MaterialRecipe updateMaterialRecipe(Long id, MaterialRecipe materialRecipeDetails) {
        Optional<MaterialRecipe> optionalMaterialRecipe = materialRecipeRepository.findById(id);
        if (optionalMaterialRecipe.isPresent()) {
            MaterialRecipe existingMaterialRecipe = optionalMaterialRecipe.get();
            existingMaterialRecipe.setRecipe(materialRecipeDetails.getRecipe());
            existingMaterialRecipe.setResource(materialRecipeDetails.getResource());
            return materialRecipeRepository.save(existingMaterialRecipe);
        } else {
            throw new RuntimeException("MaterialRecipe not found with id " + id);
        }
    }

    public void deleteMaterialRecipe(Long id) {
        materialRecipeRepository.deleteById(id);
    }
}