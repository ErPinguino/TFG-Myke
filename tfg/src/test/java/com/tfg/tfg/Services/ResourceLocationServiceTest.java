package com.tfg.tfg.Services;

import com.tfg.tfg.Entities.ResourceLocation;
import com.tfg.tfg.Repository.ResourceLocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ResourceLocationServiceTest {
    @Mock
    private ResourceLocationRepository resourceLocationRepository;

    @InjectMocks
    private ResourceLocationService resourceLocationService;

    private ResourceLocation resourceLocation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resourceLocation = new ResourceLocation();
        resourceLocation.setId(1L);
        resourceLocation.setCoordinateX(100);
        resourceLocation.setCoordinateY(64);
        resourceLocation.setCoordinateZ(-200);
    }

    @Test
    void createResourceLocation() {
        when(resourceLocationRepository.save(any(ResourceLocation.class))).thenReturn(resourceLocation);
        ResourceLocation created = resourceLocationService.createResourceLocation(resourceLocation);
        assertEquals(resourceLocation.getCoordinateX(), created.getCoordinateX());
    }

    @Test
    void findResourceLocationById() {
        when(resourceLocationRepository.findById(1L)).thenReturn(Optional.of(resourceLocation));
        ResourceLocation found = resourceLocationService.findResourceLocationById(1L);
        assertEquals(resourceLocation.getCoordinateX(), found.getCoordinateX());
    }

    @Test
    void findAllResourceLocations() {
        when(resourceLocationRepository.findAll()).thenReturn(Arrays.asList(resourceLocation));
        List<ResourceLocation> locations = resourceLocationService.findAllResourceLocations();
        assertEquals(1, locations.size());
    }

    @Test
    void updateResourceLocation() {
        when(resourceLocationRepository.findById(1L)).thenReturn(Optional.of(resourceLocation));
        when(resourceLocationRepository.save(any(ResourceLocation.class))).thenReturn(resourceLocation);

        ResourceLocation updated = resourceLocationService.updateResourceLocation(1L, resourceLocation);
        assertEquals(resourceLocation.getCoordinateX(), updated.getCoordinateX());
    }

    @Test
    void deleteResourceLocation() {
        doNothing().when(resourceLocationRepository).deleteById(1L);
        resourceLocationService.deleteResourceLocation(1L);
        verify(resourceLocationRepository).deleteById(1L);
    }
}