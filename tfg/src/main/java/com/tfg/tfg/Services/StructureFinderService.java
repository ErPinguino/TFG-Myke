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
        // Usamos la misma versión que en VillageRadarClient
        this.cubiomes = new Cubiomes(MCVersion.MC_1_21);
    }

    /**
     * Convierte coordenada de bloque a coordenada de región (en chunks).
     */
    private int blockToRegionCoord(double blockCoord, int regionSize) {
        return (int) Math.floor(blockCoord / 16.0 / regionSize);
    }

    /**
     * Busca hasta `count` estructuras de tipo `structureName`, dentro de
     * `radius` bloques alrededor de (x,z).
     */
    public StructureListOutputDTO findStructures(StructureSearchInputDTO input) {
        log.info("→ findStructures seed='{}', x={}, z={}, radiusBlocks={}, count={}, type='{}'",
                input.getSeed(), input.getX(), input.getZ(),
                input.getRadius(), input.getCount(), input.getStructureName());

        // 1) Normalizamos el nombre al enum
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
                    .message("Structure type not available: " + input.getStructureName())
                    .coordinates(List.of())
                    .build();
        }

        // 2) Aplicar seed
        long seed = Long.parseLong(input.getSeed());
        cubiomes.applySeed(Dimension.DIM_OVERWORLD, seed);

        // 3) Tamaño de región EN CHUNKS según tipo
        //    Estos valores vienen del wrapper original :contentReference[oaicite:0]{index=0}:contentReference[oaicite:1]{index=1}
        int regionSize = switch (type) {
            case Village      -> 34;
            case Ruined_Portal-> 40;
            // Añade aquí más casos si quieres afinar spacing de otros tipos
            default           -> 32;
        };
        log.info("  regionSizeChunks={} for type={}", regionSize, type);

        // 4) Centro de búsqueda en regiones
        double x = input.getX(), z = input.getZ();
        int centerRegX = blockToRegionCoord(x, regionSize);
        int centerRegZ = blockToRegionCoord(z, regionSize);
        log.info("  centerRegion = ({}, {})", centerRegX, centerRegZ);

        // 5) Convertir radius de bloques a número de regiones
        int regionRadius = input.getRadius() > 0
                ? (int) Math.ceil(input.getRadius() / (16.0 * regionSize))
                : 0;
        log.info("  regionRadius={} regions (~{} blocks)",
                regionRadius, (int)(regionRadius * 16.0 * regionSize));

        int targetCount = Math.max(1, input.getCount());
        log.info("  targetCount={}", targetCount);

        // 6) Función que prueba una región y filtra via cubiomes.isViable
        BiFunction<Integer,Integer,Optional<Pos>> probe = (rx, rz) -> {
            Pos pos = cubiomes.getStructurePos(type, seed, rx, rz);
            boolean viable = cubiomes.isViableStructurePos(type, pos.x(), pos.z());
            log.debug("    probe region ({}, {}): pos=({}, {}), viable={}",
                    rx, rz, pos.x(), pos.z(), viable);
            return viable ? Optional.of(pos) : Optional.empty();
        };

        // 7) Recogemos coordenadas
        List<Coordinate> coords = new ArrayList<>();
        // 7.1) Región central
        probe.apply(centerRegX, centerRegZ)
                .ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));

        // 7.2) Espiral alrededor
        for (int d = 1; d <= regionRadius && coords.size() < targetCount; d++) {
            // muros norte/sur
            for (int dx = -d; dx <= d && coords.size() < targetCount; dx++) {
                probe.apply(centerRegX + dx, centerRegZ - d).ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));
                probe.apply(centerRegX + dx, centerRegZ + d).ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));
            }
            // muros este/oeste
            for (int dz = -(d - 1); dz <= (d - 1) && coords.size() < targetCount; dz++) {
                probe.apply(centerRegX - d, centerRegZ + dz).ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));
                probe.apply(centerRegX + d, centerRegZ + dz).ifPresent(p -> coords.add(new Coordinate(p.x(), p.z())));
            }
        }

        boolean found = !coords.isEmpty();
        String msg = found
                ? "Found " + coords.size() + " " + normalized
                : "No structures found";

        log.info("← found {} structures of type {}", coords.size(), type);
        return StructureListOutputDTO.builder()
                .found(found)
                .message(msg)
                .coordinates(coords)
                .build();
    }
}
