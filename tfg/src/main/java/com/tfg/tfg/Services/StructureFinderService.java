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
        log.info("→ findStructures seed='{}', x={}, z={}, radiusBlocks={}, count={}, type='{}'",
                input.getSeed(), input.getX(), input.getZ(),
                input.getRadius(), input.getCount(), input.getStructureName());

        // 1) Parsear la seed
        long seed;
        try {
            seed = Long.parseLong(input.getSeed().trim());
        } catch (NumberFormatException e) {
            log.warn("Seed inválida: {}", input.getSeed());
            return StructureListOutputDTO.builder()
                    .found(false)
                    .message("Invalid seed: " + input.getSeed())
                    .coordinates(List.of())
                    .build();
        }
        cubiomes.applySeed(Dimension.DIM_OVERWORLD, seed);

        // 2) Mapeo directo del nombre al enum
        StructureType type;
        try {
            type = StructureType.valueOf(input.getStructureName());
        } catch (IllegalArgumentException e) {
            log.warn("Type not available: {}", input.getStructureName());
            return StructureListOutputDTO.builder()
                    .found(false)
                    .message("Structure type not available: " + input.getStructureName())
                    .coordinates(List.of())
                    .build();
        }

        // 3) Determinar regionSize según el tipo
        int regionSize = switch (type) {
            case Village        -> 34;
            case Ruined_Portal  -> 40;
            default             -> 32;
        };
        log.info("  regionSizeChunks={} for type={}", regionSize, type);

        // 4) Centro de búsqueda en regiones
        double x = input.getX(), z = input.getZ();
        int centerRegX = blockToRegionCoord(x, regionSize);
        int centerRegZ = blockToRegionCoord(z, regionSize);
        log.info("  centerRegion = ({}, {})", centerRegX, centerRegZ);

        // 5) Conversión de radio de bloques a regiones
        int regionRadius = input.getRadius() > 0
                ? (int) Math.ceil(input.getRadius() / (16.0 * regionSize))
                : 0;
        log.info("  regionRadius={} regions (~{} blocks)",
                regionRadius, (int)(regionRadius * 16.0 * regionSize));

        int targetCount = Math.max(1, input.getCount());
        log.info("  targetCount={}", targetCount);

        // 6) Función para probar viabilidad en cada región
        BiFunction<Integer,Integer,Optional<Pos>> probe = (rx, rz) -> {
            Pos p = cubiomes.getStructurePos(type, seed, rx, rz);
            boolean viable = cubiomes.isViableStructurePos(type, p.x(), p.z());
            log.debug("    probe region ({}, {}): pos=({}, {}), viable={}",
                    rx, rz, p.x(), p.z(), viable);
            return viable ? Optional.of(p) : Optional.empty();
        };

        // 7) Recolectar coordenadas
        List<Coordinate> coords = new ArrayList<>();
        probe.apply(centerRegX, centerRegZ)
                .ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));

        for (int d = 1; d <= regionRadius && coords.size() < targetCount; d++) {
            for (int dx = -d; dx <= d && coords.size() < targetCount; dx++) {
                probe.apply(centerRegX + dx, centerRegZ - d)
                        .ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));
                probe.apply(centerRegX + dx, centerRegZ + d)
                        .ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));
            }
            for (int dz = -(d - 1); dz <= d - 1 && coords.size() < targetCount; dz++) {
                probe.apply(centerRegX - d, centerRegZ + dz)
                        .ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));
                probe.apply(centerRegX + d, centerRegZ + dz)
                        .ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));
            }
        }

        boolean found = !coords.isEmpty();
        String message = found
                ? "Found " + coords.size() + " " + input.getStructureName()
                : "No structures found";

        log.info("← found {} structures of type {}", coords.size(), type);
        return StructureListOutputDTO.builder()
                .found(found)
                .message(message)
                .coordinates(coords)
                .build();
    }
}
