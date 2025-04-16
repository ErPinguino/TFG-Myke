package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.MaterialRecipeInPutDTO;
import com.tfg.tfg.DTOs.MaterialRecipeOutPutDTO;
import com.tfg.tfg.Entities.MaterialRecipe;
import com.tfg.tfg.Services.MaterialRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/minecraftProject/material-recipes")
public class MaterialRecipeController {

    private final MaterialRecipeService materialRecipeService;

    @Autowired
    public MaterialRecipeController(MaterialRecipeService materialRecipeService) {
        this.materialRecipeService = materialRecipeService;
    }

    // Crear un nuevo MaterialRecipe
    @PostMapping
    public ResponseEntity<MaterialRecipeOutPutDTO> createMaterialRecipe(@RequestBody MaterialRecipeInPutDTO materialRecipeInPutDTO) {
        MaterialRecipe materialRecipe = materialRecipeInPutDTO.toEntity();
        MaterialRecipe createdMaterialRecipe = materialRecipeService.createMaterialRecipe(materialRecipe);
        MaterialRecipeOutPutDTO outPutDTO = MaterialRecipeOutPutDTO.fromEntity(
                createdMaterialRecipe.getId(),
                createdMaterialRecipe.getRecipe().getId(),
                createdMaterialRecipe.getResource().getId()
        );
        return new ResponseEntity<>(outPutDTO, HttpStatus.CREATED);
    }

    // Obtener todos los MaterialRecipe
    @GetMapping
    public ResponseEntity<List<MaterialRecipeOutPutDTO>> getAllMaterialRecipes() {
        List<MaterialRecipe> materialRecipes = materialRecipeService.getAllMaterialRecipes();
        List<MaterialRecipeOutPutDTO> outPutDTOs = materialRecipes.stream()
                .map(mr -> MaterialRecipeOutPutDTO.fromEntity(mr.getId(), mr.getRecipe().getId(), mr.getResource().getId()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(outPutDTOs, HttpStatus.OK);
    }

    // Obtener un MaterialRecipe por su ID
    @GetMapping("/{id}")
    public ResponseEntity<MaterialRecipeOutPutDTO> getMaterialRecipeById(@PathVariable Long id) {
        Optional<MaterialRecipe> optionalMaterialRecipe = materialRecipeService.getMaterialRecipeById(id);
        if (optionalMaterialRecipe.isPresent()) {
            MaterialRecipe materialRecipe = optionalMaterialRecipe.get();
            MaterialRecipeOutPutDTO outPutDTO = MaterialRecipeOutPutDTO.fromEntity(
                    materialRecipe.getId(),
                    materialRecipe.getRecipe().getId(),
                    materialRecipe.getResource().getId()
            );
            return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar un MaterialRecipe existente
    @PutMapping("/{id}")
    public ResponseEntity<MaterialRecipeOutPutDTO> updateMaterialRecipe(@PathVariable Long id, @RequestBody MaterialRecipeInPutDTO materialRecipeInPutDTO) {
        MaterialRecipe materialRecipe = materialRecipeInPutDTO.toEntity();
        MaterialRecipe updatedMaterialRecipe = materialRecipeService.updateMaterialRecipe(id, materialRecipe);
        MaterialRecipeOutPutDTO outPutDTO = MaterialRecipeOutPutDTO.fromEntity(
                updatedMaterialRecipe.getId(),
                updatedMaterialRecipe.getRecipe().getId(),
                updatedMaterialRecipe.getResource().getId()
        );
        return new ResponseEntity<>(outPutDTO, HttpStatus.OK);
    }

    // Eliminar un MaterialRecipe por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterialRecipe(@PathVariable Long id) {
        materialRecipeService.deleteMaterialRecipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}