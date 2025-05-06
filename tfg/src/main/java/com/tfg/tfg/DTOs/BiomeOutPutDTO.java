package com.tfg.tfg.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BiomeOutPutDTO {
    /**
     * Si encontró o no el bioma
     */
    private boolean found;
    /**
     * Mensaje descriptivo ("Bioma encontrado", "Bioma no válido", etc.)
     */
    private String message;
    /**
     * Coordenada X del primer match (null si not found)
     */
    private Integer x;
    /**
     * Coordenada Z del primer match (null si not found)
     */
    private Integer z;
}