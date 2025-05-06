// src/main/java/com/tfg/tfg/Services/BiomeService.java
package com.tfg.tfg.Services;

import com.tfg.tfg.DTOs.BiomeInPutDTO;
import com.tfg.tfg.DTOs.BiomeListOutPutDTO;
import com.tfg.tfg.DTOs.BiomeListOutPutDTO.Coordinate;
import de.rasmusantons.cubiomes.Cubiomes;
import de.rasmusantons.cubiomes.BiomeID;
import de.rasmusantons.cubiomes.Dimension;
import de.rasmusantons.cubiomes.MCVersion;
import de.rasmusantons.cubiomes.Range;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BiomeService {
    private final Cubiomes cubiomes;
    private final int OVERWORLD_DIM_ID;

    public BiomeService() {
        // Usamos MC_1_21 para coherencia con tests
        this.cubiomes = new Cubiomes(MCVersion.MC_1_21);
        this.OVERWORLD_DIM_ID = Dimension.DIM_OVERWORLD.ordinal();
    }

    /**
     * Genera un grid 2D de biomas y retorna la lista completa de coordenadas que coincidan con el bioma objetivo.
     */
    public BiomeListOutPutDTO findBiomes(BiomeInPutDTO input) {
        String name = input.getName().toLowerCase().replace(' ', '_');
        BiomeID target;
        try {
            target = BiomeID.valueOf(name);
        } catch (IllegalArgumentException e) {
            return BiomeListOutPutDTO.builder()
                    .found(false)
                    .message("Bioma no válido: " + input.getName())
                    .build();
        }

        long seed = input.getSeed();
        int radius = input.getRadius();
        int y      = input.getY(); // default 63

        // Aplicamos la semilla
        cubiomes.applySeed(Dimension.DIM_OVERWORLD, seed);

        // Definimos el rango centrado en (0,0)
        int xStart = -radius;
        int zStart = -radius;
        int size   = radius * 2 + 1;

        // Generamos grid 2D
        Range range2d = new Range(OVERWORLD_DIM_ID, xStart, zStart, size, size, y);
        BiomeID[][] grid = cubiomes.genBiomes2D(range2d);

        // Acumulamos todas las coincidencias
        List<Coordinate> coords = new ArrayList<>();
        for (int dz = 0; dz < grid.length; dz++) {
            for (int dx = 0; dx < grid[dz].length; dx++) {
                if (grid[dz][dx] == target) {
                    int x = xStart + dx;
                    int z = zStart + dz;
                    coords.add(new Coordinate(x, z));
                }
            }
        }

        if (coords.isEmpty()) {
            return BiomeListOutPutDTO.builder()
                    .found(false)
                    .message("Bioma no encontrado en un radio de " + radius + " bloques")
                    .build();
        }

        return BiomeListOutPutDTO.builder()
                .found(true)
                .message("Biomas encontrados: " + coords.size())
                .coordinates(coords)
                .build();
    }
}