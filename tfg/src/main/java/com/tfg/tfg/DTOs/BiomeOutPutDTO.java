package com.tfg.tfg.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BiomeOutPutDTO {
    private Long id;
    private String name;
    private List<ResourceLocationSimpleDTO> resourceLocations;

    @Data
    public static class ResourceLocationSimpleDTO {
        private Long id;
        private int x;
        private int y;
        private int z;
    }
}
