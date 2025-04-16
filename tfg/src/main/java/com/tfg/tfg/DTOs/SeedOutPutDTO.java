package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.Seed;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SeedOutPutDTO {

    private Long id;

    private String seed_value;

    private List<ResourceLocationOutPutDTO> resourceLocations;

    public static SeedOutPutDTO fromEntity(Seed seed) {
        return SeedOutPutDTO.builder()
                .id(seed.getId())
                .seed_value(seed.getSeed_value())
                .resourceLocations(seed.getResourceLocationList().stream()
                        .map(ResourceLocationOutPutDTO::fromEntity)
                        .toList())
                .build();
    }

}
