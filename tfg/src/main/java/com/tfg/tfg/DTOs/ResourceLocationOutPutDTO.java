package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.ResourceLocation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceLocationOutPutDTO {
    private Long id;
    private int coordinateX;
    private int coordinateY;
    private int coordinateZ;
    private Long seedId;
    private Long resourceId;
    private Long biomeId;

    public static ResourceLocationOutPutDTO fromEntity(ResourceLocation resourceLocation) {
        return ResourceLocationOutPutDTO.builder()
                .id(resourceLocation.getId())
                .coordinateX(resourceLocation.getCoordinateX())
                .coordinateY(resourceLocation.getCoordinateY())
                .coordinateZ(resourceLocation.getCoordinateZ())
                .seedId(resourceLocation.getSeed().getId())
                .resourceId(resourceLocation.getResource().getId())
                .biomeId(resourceLocation.getBiome().getId())
                .build();
    }
}
