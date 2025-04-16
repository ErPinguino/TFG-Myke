package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.ResourceLocationInPutDTO;
import com.tfg.tfg.DTOs.ResourceLocationOutPutDTO;
import com.tfg.tfg.Entities.ResourceLocation;
import com.tfg.tfg.Services.ResourceLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/minecraftProject/resource-locations")
public class ResourceLocationController {

    @Autowired
    private ResourceLocationService resourceLocationService;

    @PostMapping
    public ResponseEntity<ResourceLocationOutPutDTO> createResourceLocation(
            @RequestBody ResourceLocationInPutDTO resourceLocationInPutDTO) {
        ResourceLocation resourceLocation = resourceLocationInPutDTO.toEntity();
        ResourceLocation createdResourceLocation = resourceLocationService.createResourceLocation(resourceLocation);
        return new ResponseEntity<>(ResourceLocationOutPutDTO.fromEntity(createdResourceLocation), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceLocationOutPutDTO> getResourceLocationById(@PathVariable Long id) {
        ResourceLocation resourceLocation = resourceLocationService.findResourceLocationById(id);
        return ResponseEntity.ok(ResourceLocationOutPutDTO.fromEntity(resourceLocation));
    }

    @GetMapping
    public ResponseEntity<List<ResourceLocationOutPutDTO>> getAllResourceLocations() {
        List<ResourceLocation> resourceLocations = resourceLocationService.findAllResourceLocations();
        List<ResourceLocationOutPutDTO> dtos = resourceLocations.stream()
                .map(ResourceLocationOutPutDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResourceLocationOutPutDTO> updateResourceLocation(
            @PathVariable Long id,
            @RequestBody ResourceLocationInPutDTO resourceLocationInPutDTO) {

        ResourceLocation updatedResourceLocation = resourceLocationInPutDTO.toEntity();
        ResourceLocation savedResourceLocation = resourceLocationService.updateResourceLocation(id, updatedResourceLocation);
        return ResponseEntity.ok(ResourceLocationOutPutDTO.fromEntity(savedResourceLocation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResourceLocation(@PathVariable Long id) {
        resourceLocationService.deleteResourceLocation(id);
        return ResponseEntity.noContent().build();
    }


}