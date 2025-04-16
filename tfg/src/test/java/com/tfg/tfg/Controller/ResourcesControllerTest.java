package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.ResourcesInPutDTO;
import com.tfg.tfg.DTOs.ResourcesOutPutDTO;
import com.tfg.tfg.Entities.Resources;
import com.tfg.tfg.Services.ResourcesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ResourcesControllerTest {

    @Mock
    private ResourcesService resourcesService;

    @InjectMocks
    private ResourcesController resourcesController;

    private Resources resource;
    private ResourcesInPutDTO inputDTO;

    @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);

            // Given común
            resource = Resources.builder()
                .id(1L)
                .name("Iron Ore")
                .locations(new ArrayList<>())
                .materialRecipe(new ArrayList<>())
                .build();

            inputDTO = ResourcesInPutDTO.builder()
                .name("Iron Ore")
                .locations(new ArrayList<>())
                .materialRecipe(new ArrayList<>())
                .build();
        }

    @Test
    void createResource_ShouldReturnCreatedResource() {
        // Given
        when(resourcesService.createResource(any(Resources.class)))
                .thenReturn(resource);

        // When
        ResponseEntity<ResourcesOutPutDTO> response =
                resourcesController.createResource(inputDTO);

        // Then
        verify(resourcesService).createResource(any(Resources.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Iron Ore", response.getBody().getName());
    }

    @Test
    void getResourceById_ShouldReturnResource() {
        // Given
        when(resourcesService.findResourceById(1L))
            .thenReturn(resource);

        // When
        ResponseEntity<ResourcesOutPutDTO> response =
            resourcesController.getResourceById(1L);

        // Then
        verify(resourcesService).findResourceById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Iron Ore", response.getBody().getName());
    }

    @Test
    void getAllResources_ShouldReturnListOfResources() {
        // Given
        List<Resources> resources = Arrays.asList(resource);
        when(resourcesService.findAllResources())
            .thenReturn(resources);

        // When
        ResponseEntity<List<ResourcesOutPutDTO>> response =
            resourcesController.getAllResources();

        // Then
        verify(resourcesService).findAllResources();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(1L, response.getBody().get(0).getId());
        assertEquals("Iron Ore", response.getBody().get(0).getName());
    }

    @Test
    void updateResource_ShouldReturnUpdatedResource() {
        // Given
        when(resourcesService.updateResource(eq(1L), any(Resources.class)))
            .thenReturn(resource);

        // When
        ResponseEntity<ResourcesOutPutDTO> response =
            resourcesController.updateResource(1L, inputDTO);

        // Then
        verify(resourcesService).updateResource(eq(1L), any(Resources.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Iron Ore", response.getBody().getName());
    }

    @Test
    void deleteResource_ShouldReturnNoContent() {
        // Given
        doNothing().when(resourcesService).deleteResource(1L);

        // When
        ResponseEntity<Void> response = resourcesController.deleteResource(1L);

        // Then
        verify(resourcesService).deleteResource(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}