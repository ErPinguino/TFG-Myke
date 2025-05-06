package com.tfg.tfg.Services;

import com.tfg.tfg.DTOs.StructureSearchInputDTO;
import com.tfg.tfg.DTOs.StructureListOutputDTO;
import com.tfg.tfg.DTOs.StructureListOutputDTO.Coordinate;
import de.rasmusantons.cubiomes.Cubiomes;
import de.rasmusantons.cubiomes.Dimension;
import de.rasmusantons.cubiomes.MCVersion;
import de.rasmusantons.cubiomes.Pos;
import de.rasmusantons.cubiomes.StructureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

@Service
public class StructureFinderService {
    private static final Logger log = LoggerFactory.getLogger(StructureFinderService.class);
    private final Cubiomes cubiomes;

    public StructureFinderService() {
        this.cubiomes = new Cubiomes(MCVersion.MC_1_21);
    }

    private int blockToRegionCoord(double blockCoord, int regionSize) {
        return (int) Math.floor(blockCoord / 16.0 / regionSize);
    }

    public StructureListOutputDTO findStructures(StructureSearchInputDTO input) {
        log.info("→ findStructures seed={}, x={}, z={}, radiusBlocks={}, count={}",
                input.getSeed(), input.getX(), input.getZ(), input.getRadius(), input.getCount());

        // 1) Parseamos la seed desde String a long
        long seedLong;
        try {
            seedLong = Long.parseLong(input.getSeed().trim());
        } catch (NumberFormatException e) {
            log.warn("Seed inválida: {}", input.getSeed());
            return StructureListOutputDTO.builder()
                    .found(false)
                    .message("Invalid seed: " + input.getSeed())
                    .coordinates(List.of())
                    .build();
        }
        cubiomes.applySeed(Dimension.DIM_OVERWORLD, seedLong);

        // 2) Normalizamos el nombre para el enum
        String normalized = java.util.Arrays.stream(input.getStructureName().split("[ _]+"))
                .map(w -> w.substring(0,1).toUpperCase() + w.substring(1).toLowerCase())
                .collect(java.util.stream.Collectors.joining());

        StructureType type;
        try {
            type = StructureType.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            log.warn("Type not available: {}", input.getStructureName());
            return StructureListOutputDTO.builder()
                    .found(false)
                    .message("Type not available: " + input.getStructureName())
                    .coordinates(List.of())
                    .build();
        }

        // 3) Tamaño de región y centro en regiones
        int regionSize = type == StructureType.Village ? 34 : 32;
        int centerRegX = blockToRegionCoord(input.getX(), regionSize);
        int centerRegZ = blockToRegionCoord(input.getZ(), regionSize);

        // 4) Convertimos radio de bloques a regiones
        int regionRadius = input.getRadius() > 0
                ? (int) Math.ceil(input.getRadius() / (16.0 * regionSize))
                : 0;

        int targetCount = Math.max(1, input.getCount());

        // 5) Función auxiliar
        BiFunction<Integer,Integer,Optional<Pos>> probe = (rx, rz) -> {
            Pos pos = cubiomes.getStructurePos(type, seedLong, rx, rz);
            return cubiomes.isViableStructurePos(type, pos.x(), pos.z())
                    ? Optional.of(pos)
                    : Optional.empty();
        };

        List<Coordinate> coords = new ArrayList<>();
        // Región central
        probe.apply(centerRegX, centerRegZ)
                .ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));

        // Espiral
        for (int d = 1; d <= regionRadius && coords.size() < targetCount; d++) {
            for (int dx = -d; dx <= d && coords.size() < targetCount; dx++) {
                probe.apply(centerRegX + dx, centerRegZ - d).ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));
                probe.apply(centerRegX + dx, centerRegZ + d).ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));
            }
            for (int dz = -(d - 1); dz <= d - 1 && coords.size() < targetCount; dz++) {
                probe.apply(centerRegX - d, centerRegZ + dz).ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));
                probe.apply(centerRegX + d, centerRegZ + dz).ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));
            }
        }

        boolean found = !coords.isEmpty();
        String message = found
                ? "Found " + coords.size() + " " + normalized
                : "No structures found";

        return StructureListOutputDTO.builder()
                .found(found)
                .message(message)
                .coordinates(coords)
                .build();
    }
}
