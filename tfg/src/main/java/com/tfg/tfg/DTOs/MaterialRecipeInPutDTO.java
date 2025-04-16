package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.MaterialRecipe;
import com.tfg.tfg.Entities.Recipe;
import com.tfg.tfg.Entities.Resources;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaterialRecipeInPutDTO {
    private Long recipeId;
    private Long resourceId;

    public MaterialRecipe toEntity() {
        return MaterialRecipe.builder()
                .recipe(Recipe.builder().id(this.recipeId).build())
                .resource(Resources.builder().id(this.resourceId).build())
                .build();
    }
}
