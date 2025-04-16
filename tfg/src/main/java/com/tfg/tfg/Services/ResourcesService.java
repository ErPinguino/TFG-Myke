package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.Resources;
import com.tfg.tfg.Repository.ResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesService {

    @Autowired
    private ResourcesRepository resourcesRepository;

    public Resources createResource(Resources resource) {
        return resourcesRepository.save(resource);
    }

    public Resources findResourceById(Long id) {
        return resourcesRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found"));
    }

    public List<Resources> findAllResources() {
        return resourcesRepository.findAll();
    }

    public Resources updateResource(Long id, Resources updatedResource) {
        Resources existingResource = findResourceById(id);
        existingResource.setName(updatedResource.getName());
        return resourcesRepository.save(existingResource);
    }

    public void deleteResource(Long id) {
        resourcesRepository.deleteById(id);
    }
}