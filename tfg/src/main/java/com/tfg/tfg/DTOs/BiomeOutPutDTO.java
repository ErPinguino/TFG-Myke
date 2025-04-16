package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.Biome;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class BiomeOutPutDTO {
    private Long id;
    private String name;
    private List<ResourceLocationOutPutDTO> resourceLocations; // Usa ResourceLocationOutPutDTO

    public static BiomeOutPutDTO fromEntity(Biome biome) {
        return BiomeOutPutDTO.builder()
                .id(biome.getId())
                .name(biome.getName())
                .resourceLocations(biome.getLocations().stream()
                        .map(ResourceLocationOutPutDTO::fromEntity) // Mapea a ResourceLocationOutPutDTO
                        .collect(Collectors.toList()))
                .build();
    }
}