package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.Biome;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BiomeInPutDTO {
    private String name;


    public Biome toEntity() {
        return Biome.builder()
                .name(this.name)
                .build();
    }

}


