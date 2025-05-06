package com.tfg.tfg.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BiomeInPutDTO {
    private long seed;
    private String name;
    @Builder.Default
    private int radius = 2000;
    @Builder.Default
    private int step   = 16;
    /** altura por defecto = 63 para coincidir con los tests */
    @Builder.Default
    private int y      = 63;
}