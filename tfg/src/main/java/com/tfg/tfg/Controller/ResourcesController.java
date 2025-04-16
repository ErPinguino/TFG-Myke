package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.ResourcesInPutDTO;
import com.tfg.tfg.DTOs.ResourcesOutPutDTO;
import com.tfg.tfg.Entities.Resources;
import com.tfg.tfg.Services.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/minecraftProject/resources")
public class ResourcesController {

    @Autowired
    private ResourcesService resourcesService;

    @PostMapping
    public ResponseEntity<ResourcesOutPutDTO> createResource(@RequestBody ResourcesInPutDTO resourcesInPutDTO) {
        Resources resource = ResourcesInPutDTO.toEntity(resourcesInPutDTO);
        Resources createdResource = resourcesService.createResource(resource);
        return new ResponseEntity<>(ResourcesOutPutDTO.fromEntity(createdResource), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourcesOutPutDTO> getResourceById(@PathVariable Long id) {
        Resources resource = resourcesService.findResourceById(id);
        return ResponseEntity.ok(ResourcesOutPutDTO.fromEntity(resource));
    }

    @GetMapping
    public ResponseEntity<List<ResourcesOutPutDTO>> getAllResources() {
        List<Resources> resources = resourcesService.findAllResources();
        List<ResourcesOutPutDTO> dtos = resources.stream()
                .map(ResourcesOutPutDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourcesOutPutDTO> updateResource(
            @PathVariable Long id,
            @RequestBody ResourcesInPutDTO resourcesInPutDTO) {

        Resources resourceToUpdate = ResourcesInPutDTO.toEntity(resourcesInPutDTO);
        Resources updatedResource = resourcesService.updateResource(id, resourceToUpdate);
        return ResponseEntity.ok(ResourcesOutPutDTO.fromEntity(updatedResource));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        resourcesService.deleteResource(id);
        return ResponseEntity.noContent().build();
    }
}