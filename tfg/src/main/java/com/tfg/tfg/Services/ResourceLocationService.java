package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.ResourceLocation;
import com.tfg.tfg.Repository.ResourceLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceLocationService {

    @Autowired
    private ResourceLocationRepository resourceLocationRepository;

    public ResourceLocation createResourceLocation(ResourceLocation resourceLocation) {
        return resourceLocationRepository.save(resourceLocation);
    }

    public ResourceLocation findResourceLocationById(Long id) {
        return resourceLocationRepository.findById(id).orElseThrow(() -> new RuntimeException("ResourceLocation not found"));
    }

    public List<ResourceLocation> findAllResourceLocations() {
        return resourceLocationRepository.findAll();
    }

    public ResourceLocation updateResourceLocation(Long id, ResourceLocation updatedResourceLocation) {
        ResourceLocation existingResourceLocation = findResourceLocationById(id);
        existingResourceLocation.setCoordinateX(updatedResourceLocation.getCoordinateX());
        existingResourceLocation.setCoordinateY(updatedResourceLocation.getCoordinateY());
        existingResourceLocation.setCoordinateZ(updatedResourceLocation.getCoordinateZ());
        return resourceLocationRepository.save(existingResourceLocation);
    }

    public void deleteResourceLocation(Long id) {
        resourceLocationRepository.deleteById(id);
    }
}