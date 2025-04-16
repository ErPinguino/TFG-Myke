package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.MaterialRecipe;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeInPutDTO {
    private String name;
    private String object_name;
    private String imageUrl;
    private List<MaterialRecipe> materialRecipes;

    public RecipeInPutDTO toEntity() {
        return RecipeInPutDTO.builder()
                .name(this.name)
                .object_name(this.object_name)
                .imageUrl(this.imageUrl)
                .materialRecipes(this.materialRecipes)
                .build();
    }


}
