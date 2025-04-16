package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.ResourceLocationInPutDTO;
import com.tfg.tfg.DTOs.ResourceLocationOutPutDTO;
import com.tfg.tfg.Entities.Biome;
import com.tfg.tfg.Entities.ResourceLocation;
import com.tfg.tfg.Entities.Resources;
import com.tfg.tfg.Entities.Seed;
import com.tfg.tfg.Services.ResourceLocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ResourceLocationControllerTest {

    @Mock
    private ResourceLocationService resourceLocationService;

    @InjectMocks
    private ResourceLocationController resourceLocationController;

    private ResourceLocation resourceLocation;
    private ResourceLocationInPutDTO inputDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Given común
        Seed seed = Seed.builder()
            .id(1L)
            .build();

        Resources resource = Resources.builder()
            .id(1L)
            .build();

        Biome biome = Biome.builder()
            .id(1L)
            .build();

        resourceLocation = ResourceLocation.builder()
            .id(1L)
            .coordinateX(100)
            .coordinateY(64)
            .coordinateZ(-200)
            .seed(seed)
            .resource(resource)
            .biome(biome)
            .build();

        inputDTO = ResourceLocationInPutDTO.builder()
            .coordinateX(100)
            .coordinateY(64)
            .coordinateZ(-200)
            .seedId(1L)
            .resourceId(1L)
            .biomeId(1L)
            .build();
    }

    @Test
    void createResourceLocation_ShouldReturnCreatedResourceLocation() {
        // Given
        when(resourceLocationService.createResourceLocation(any(ResourceLocation.class)))
            .thenReturn(resourceLocation);

        // When
        ResponseEntity<ResourceLocationOutPutDTO> response =
            resourceLocationController.createResourceLocation(inputDTO);

        // Then
        verify(resourceLocationService).createResourceLocation(any(ResourceLocation.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals(100, response.getBody().getCoordinateX());
        assertEquals(64, response.getBody().getCoordinateY());
        assertEquals(-200, response.getBody().getCoordinateZ());
    }

    @Test
    void getResourceLocationById_ShouldReturnResourceLocation() {
        // Given
        when(resourceLocationService.findResourceLocationById(1L))
            .thenReturn(resourceLocation);

        // When
        ResponseEntity<ResourceLocationOutPutDTO> response =
            resourceLocationController.getResourceLocationById(1L);

        // Then
        verify(resourceLocationService).findResourceLocationById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void getAllResourceLocations_ShouldReturnListOfResourceLocations() {
        // Given
        List<ResourceLocation> resourceLocations = Arrays.asList(resourceLocation);
        when(resourceLocationService.findAllResourceLocations())
            .thenReturn(resourceLocations);

        // When
        ResponseEntity<List<ResourceLocationOutPutDTO>> response =
            resourceLocationController.getAllResourceLocations();

        // Then
        verify(resourceLocationService).findAllResourceLocations();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(1L, response.getBody().get(0).getId());
    }

    @Test
    void updateResourceLocation_ShouldReturnUpdatedResourceLocation() {
        // Given
        when(resourceLocationService.updateResourceLocation(eq(1L), any(ResourceLocation.class)))
            .thenReturn(resourceLocation);

        // When
        ResponseEntity<ResourceLocationOutPutDTO> response =
            resourceLocationController.updateResourceLocation(1L, inputDTO);

        // Then
        verify(resourceLocationService).updateResourceLocation(eq(1L), any(ResourceLocation.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    void deleteResourceLocation_ShouldReturnNoContent() {
        // Given
        doNothing().when(resourceLocationService).deleteResourceLocation(1L);

        // When
        ResponseEntity<Void> response = resourceLocationController.deleteResourceLocation(1L);

        // Then
        verify(resourceLocationService).deleteResourceLocation(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}