package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.ResourceLocation;
import com.tfg.tfg.Entities.Resources;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ResourcesInPutDTO {
    private String name;
    private List<ResourceLocation> locations;
    private List<MaterialRecipeInPutDTO> materialRecipe;

    public static Resources toEntity(ResourcesInPutDTO dto) {
        return Resources.builder()
                .name(dto.getName())
                .locations(dto.getLocations())
                .materialRecipe(dto.getMaterialRecipe().stream()
                        .map(MaterialRecipeInPutDTO::toEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
