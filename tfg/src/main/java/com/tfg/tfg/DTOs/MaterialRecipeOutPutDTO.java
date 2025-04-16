package com.tfg.tfg.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaterialRecipeOutPutDTO {
    private Long id;
    private Long recipeId;
    private Long resourceId;

    public static MaterialRecipeOutPutDTO fromEntity(Long id, Long recipeId, Long resourceId) {
        return MaterialRecipeOutPutDTO.builder()
                .id(id)
                .recipeId(recipeId)
                .resourceId(resourceId)
                .build();
    }
}
