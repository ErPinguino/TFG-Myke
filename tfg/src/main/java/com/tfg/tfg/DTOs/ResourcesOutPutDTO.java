package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.ResourceLocation;
import com.tfg.tfg.Entities.Resources;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResourcesOutPutDTO {
    private Long id;

    private String name;

    private List<ResourceLocation> locations;

    private List<MaterialRecipeOutPutDTO> materialRecipe;



    public static ResourcesOutPutDTO fromEntity(Resources resources) {
        return ResourcesOutPutDTO.builder()
                .id(resources.getId())
                .name(resources.getName())
                .locations(resources.getLocations())
                .materialRecipe(resources.getMaterialRecipe().stream()
                        .map(materialRecipe -> MaterialRecipeOutPutDTO.fromEntity(
                                materialRecipe.getId(),
                                materialRecipe.getRecipe().getId(),
                                materialRecipe.getResource().getId()))
                        .toList())
                .build();
    }
}
