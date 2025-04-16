package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.ResourceLocation;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResourceLocationInPutDTO {
    private int coordinateX;
    private int coordinateY;
    private int coordinateZ;
    private Long seedId;
    private Long resourceId;
    private Long biomeId;

    public ResourceLocation toEntity() {
        return ResourceLocation.builder()
                .coordinateX(this.coordinateX)
                .coordinateY(this.coordinateY)
                .coordinateZ(this.coordinateZ)
                .build();
    }
}
