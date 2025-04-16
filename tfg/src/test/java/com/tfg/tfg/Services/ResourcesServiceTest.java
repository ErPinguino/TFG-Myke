package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.Resources;
import com.tfg.tfg.Repository.ResourcesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ResourcesServiceTest {
    @Mock
    private ResourcesRepository resourcesRepository;

    @InjectMocks
    private ResourcesService resourcesService;

    private Resources resource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resource = Resources.builder()
                .id(1L)
                .name("Diamond")
                .locations(new ArrayList<>())
                .materialRecipe(new ArrayList<>())
                .build();
    }

    @Test
    void createResource() {
        when(resourcesRepository.save(any(Resources.class))).thenReturn(resource);
        Resources created = resourcesService.createResource(resource);
        assertEquals(resource.getName(), created.getName());
        verify(resourcesRepository).save(any(Resources.class));
    }

    @Test
    void findResourceById() {
        when(resourcesRepository.findById(1L)).thenReturn(Optional.of(resource));
        Resources found = resourcesService.findResourceById(1L);
        assertEquals(resource.getName(), found.getName());
    }

    @Test
    void findAllResources() {
        List<Resources> resources = Arrays.asList(resource);
        when(resourcesRepository.findAll()).thenReturn(resources);
        List<Resources> found = resourcesService.findAllResources();
        assertEquals(1, found.size());
    }

    @Test
    void updateResource() {
        when(resourcesRepository.findById(1L)).thenReturn(Optional.of(resource));
        when(resourcesRepository.save(any(Resources.class))).thenReturn(resource);

        Resources updated = resourcesService.updateResource(1L, resource);
        assertEquals(resource.getName(), updated.getName());
        verify(resourcesRepository).save(any(Resources.class));
    }

    @Test
    void deleteResource() {
        doNothing().when(resourcesRepository).deleteById(1L);
        resourcesService.deleteResource(1L);
        verify(resourcesRepository).deleteById(1L);
    }
}