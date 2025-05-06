package com.tfg.tfg.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StructureListOutputDTO {

    /**
     * Indica si se encontraron estructuras.
     */
    private boolean found;

    /**
     * Mensaje de resultado ("Found X Village", "No structures found", etc.).
     */
    private String message;

    /**
     * Lista de coordenadas de bloque de las estructuras encontradas.
     */
    private List<Coordinate> coordinates;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Coordinate {
        /**
         * Coordenada X en bloques.
         */
        private int x;

        /**
         * Coordenada Z en bloques.
         */
        private int z;
    }
}
