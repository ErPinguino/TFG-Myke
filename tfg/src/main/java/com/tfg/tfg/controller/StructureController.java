package com.tfg.tfg.controller;

import com.tfg.tfg.dto.StructureSearchInputDTO;
import com.tfg.tfg.dto.StructureListOutputDTO;
import com.tfg.tfg.services.StructureFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/minecraftProject/structures")
@CrossOrigin(origins = "*")
public class StructureController {

    private final StructureFinderService finderService;

    @Autowired
    public StructureController(StructureFinderService finderService) {
        this.finderService = finderService;
    }

    @PostMapping("/search")
    public ResponseEntity<StructureListOutputDTO> searchStructures(
            @RequestBody StructureSearchInputDTO input) {
        return ResponseEntity.ok(finderService.findStructures(input));
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getStructureTypes() {
        List<String> types = List.of(
                "Village",
                "Desert_Pyramid",
                "Jungle_Pyramid",
                "Jungle_Temple",
                "Swamp_Hut",
                "Igloo",
                "Ocean_Ruin",
                "Shipwreck",
                "Monument",
                "Mansion",
                "Outpost",
                "Ruined_Portal",
                "Desert_Well"
        );
        return ResponseEntity.ok(types);
    }
}
