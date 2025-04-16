package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.ResourceLocation;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SeedInPutDTO {

    private String seed_value;

    private List<ResourceLocationOutPutDTO> resourceLocations;

    public static SeedInPutDTO fromEntity(com.tfg.tfg.Entities.Seed seed) {
        return SeedInPutDTO.builder()
                .seed_value(seed.getSeed_value())
                .resourceLocations(seed.getResourceLocationList().stream()
                        .map(ResourceLocationOutPutDTO::fromEntity)
                        .toList())
                .build();
    }

}
