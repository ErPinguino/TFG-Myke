package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.StructureSearchInputDTO;
import com.tfg.tfg.DTOs.StructureListOutputDTO;
import com.tfg.tfg.Services.StructureFinderService;
import de.rasmusantons.cubiomes.StructureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/minecraftProject/structures")
@CrossOrigin(origins = "*")
public class StructureController {

    private final StructureFinderService finderService;

    @Autowired
    public StructureController(StructureFinderService finderService) {
        this.finderService = finderService;
    }

    /**
     * POST /minecraftProject/structures/search
     * Body JSON:
     * {
     *   "seed": -1959377614551870624,
     *   "structureName": "Village",
     *   "x": 0,
     *   "z": 0,
     *   "radius": 2000,
     *   "count": 5
     * }
     * @return listado de hasta `count` estructuras encontradas
     */
    @PostMapping("/search")
    public ResponseEntity<StructureListOutputDTO> searchStructures(
            @RequestBody StructureSearchInputDTO input
    ) {
        StructureListOutputDTO result = finderService.findStructures(input);
        return ResponseEntity.ok(result);
    }

    /**
     * GET /minecraftProject/structures/types
     * Devuelve lista de tipos de estructuras disponibles
     */
    @GetMapping("/types")
    public ResponseEntity<List<String>> getStructureTypes() {
        List<String> types = Arrays.stream(StructureType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(types);
    }
}
