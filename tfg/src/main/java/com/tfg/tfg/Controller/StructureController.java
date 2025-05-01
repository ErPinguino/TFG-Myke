package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.StructureSearchInputDTO;
import com.tfg.tfg.DTOs.StructureSearchOutputDTO;
import com.tfg.tfg.Services.StructureFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/minecraftProject/structures")
@CrossOrigin(origins = "*")
public class StructureController {

    @Autowired
    private StructureFinderService structureFinderService;

    @PostMapping("/find")
    public ResponseEntity<?> findStructure(@RequestBody StructureSearchInputDTO search) {
        try {
            System.out.println("Received search request: " + search.getStructureName() + ", seed: " + search.getSeed());
            StructureSearchOutputDTO result = structureFinderService.findStructure(search);
            System.out.println("Search result: " + (result.isSuccess() ? "Success" : "Failed") + " - " + result.getMessage());
            
            if (!result.isSuccess()) {
                return ResponseEntity.badRequest().body(result.getMessage());
            }
            
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.err.println("Error in findStructure: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest()
                .body("Error finding structure: " + e.getMessage());
        }
    }

    @GetMapping("/available")
    public ResponseEntity<Map<String, Integer>> getAvailableStructures() {
        return ResponseEntity.ok(structureFinderService.getAvailableStructures());
    }
}