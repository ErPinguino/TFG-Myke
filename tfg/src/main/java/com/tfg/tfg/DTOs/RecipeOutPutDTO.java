package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.MaterialRecipe;
import com.tfg.tfg.Entities.Recipe;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeOutPutDTO {
    private Long id;
    private String object_name;
    private String imageUrl;
    private List<MaterialRecipe> materialRecipes;


    public static RecipeOutPutDTO fromEntity(Recipe recipe) {
        return RecipeOutPutDTO.builder()
                .id(recipe.getId())
                .object_name(recipe.getObject_name())
                .imageUrl(recipe.getImageUrl())
                .materialRecipes(recipe.getMaterials())
                .build();
    }
}
