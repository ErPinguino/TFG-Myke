package com.tfg.tfg.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de entrada para búsqueda de estructuras.
 * La seed se recibe como String para preservar todos sus dígitos.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StructureSearchInputDTO {

    /**
     * Seed de Minecraft (como texto para no perder precisión)
     */
    private String seed;

    /**
     * Coordenada de bloque X donde empieza la búsqueda.
     */
    private Double x;

    /**
     * Coordenada de bloque Z donde empieza la búsqueda.
     */
    private Double z;

    /**
     * Radio máximo en bloques donde buscar.
     */
    private Integer radius;

    /**
     * Número máximo de resultados a devolver.
     */
    private Integer count;

    /**
     * Nombre de la estructura a buscar (p.ej. "Village").
     */
    private String structureName;
}
