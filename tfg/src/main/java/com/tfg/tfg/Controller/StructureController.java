package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.StructureSearchDTO;
import com.tfg.tfg.Services.StructureFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/minecraftProject/structures")
@CrossOrigin(origins = "*")
public class StructureController {

    @Autowired
    private StructureFinderService structureFinderService;

    @PostMapping("/find")
    public ResponseEntity<?> findStructure(@RequestBody StructureSearchDTO search) {
        try {
            int[] coords = structureFinderService.findStructure(search);
            String biome = structureFinderService.getBiomeAt(coords[0], 64, coords[1]);
            
            Map<String, Object> response = new HashMap<>();
            response.put("x", coords[0]);
            response.put("z", coords[1]);
            response.put("biome", biome);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                .body("Error finding structure: " + e.getMessage());
        }
    }
}