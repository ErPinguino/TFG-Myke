package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.SeedInPutDTO;
import com.tfg.tfg.DTOs.SeedOutPutDTO;
import com.tfg.tfg.Entities.Seed;
import com.tfg.tfg.Services.SeedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SeedControllerTest {

    @Mock
    private SeedService seedService;

    @InjectMocks
    private SeedController seedController;

    private Seed seed;
    private SeedInPutDTO inputDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Given común
        seed = Seed.builder()
            .id(1L)
            .seed_value("12345678")
            .resourceLocationList(new ArrayList<>())
            .build();

        inputDTO = SeedInPutDTO.builder()
            .seed_value("12345678")
            .resourceLocations(new ArrayList<>())
            .build();
    }

    @Test
    void createSeed_ShouldReturnCreatedSeed() {
        // Given
        when(seedService.createSeed(any(Seed.class)))
            .thenReturn(seed);

        // When
        ResponseEntity<SeedOutPutDTO> response =
            seedController.createSeed(inputDTO);

        // Then
        verify(seedService).createSeed(any(Seed.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("12345678", response.getBody().getSeed_value());
    }

    @Test
    void getAllSeeds_ShouldReturnListOfSeeds() {
        // Given
        List<Seed> seeds = Arrays.asList(seed);
        when(seedService.getAllSeeds())
            .thenReturn(seeds);

        // When
        ResponseEntity<List<SeedOutPutDTO>> response =
            seedController.getAllSeeds();

        // Then
        verify(seedService).getAllSeeds();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(1L, response.getBody().get(0).getId());
        assertEquals("12345678", response.getBody().get(0).getSeed_value());
    }

    @Test
    void getSeedById_WhenSeedExists_ShouldReturnSeed() {
        // Given
        when(seedService.getSeedById(1L))
            .thenReturn(Optional.of(seed));

        // When
        ResponseEntity<SeedOutPutDTO> response =
            seedController.getSeedById(1L);

        // Then
        verify(seedService).getSeedById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("12345678", response.getBody().getSeed_value());
    }

    @Test
    void getSeedById_WhenSeedDoesNotExist_ShouldReturnNotFound() {
        // Given
        when(seedService.getSeedById(1L))
            .thenReturn(Optional.empty());

        // When
        ResponseEntity<SeedOutPutDTO> response =
            seedController.getSeedById(1L);

        // Then
        verify(seedService).getSeedById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateSeed_WhenSeedExists_ShouldReturnUpdatedSeed() {
        // Given
        when(seedService.getSeedById(1L))
            .thenReturn(Optional.of(seed));
        when(seedService.updateSeed(eq(1L), any(Seed.class)))
            .thenReturn(seed);

        // When
        ResponseEntity<SeedOutPutDTO> response =
            seedController.updateSeed(1L, inputDTO);

        // Then
        verify(seedService).getSeedById(1L);
        verify(seedService).updateSeed(eq(1L), any(Seed.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("12345678", response.getBody().getSeed_value());
    }

    @Test
    void updateSeed_WhenSeedDoesNotExist_ShouldReturnNotFound() {
        // Given
        when(seedService.getSeedById(1L))
            .thenReturn(Optional.empty());

        // When
        ResponseEntity<SeedOutPutDTO> response =
            seedController.updateSeed(1L, inputDTO);

        // Then
        verify(seedService).getSeedById(1L);
        verify(seedService, never()).updateSeed(anyLong(), any(Seed.class));
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteSeed_ShouldReturnNoContent() {
        // Given
        doNothing().when(seedService).deleteSeed(1L);

        // When
        ResponseEntity<Void> response = seedController.deleteSeed(1L);

        // Then
        verify(seedService).deleteSeed(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getSeedsByUser_ShouldReturnListOfSeeds() {
        // Given
        List<Seed> seeds = Arrays.asList(seed);
        when(seedService.getSeedsByUser(1L))
            .thenReturn(seeds);

        // When
        ResponseEntity<List<SeedOutPutDTO>> response =
            seedController.getSeedsByUser(1L);

        // Then
        verify(seedService).getSeedsByUser(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(1L, response.getBody().get(0).getId());
        assertEquals("12345678", response.getBody().get(0).getSeed_value());
    }
}