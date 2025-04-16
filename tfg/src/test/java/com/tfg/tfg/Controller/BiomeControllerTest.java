package com.tfg.tfg.Controller;

import com.tfg.tfg.DTOs.BiomeOutPutDTO;
import com.tfg.tfg.Entities.Biome;
import com.tfg.tfg.Services.BiomeService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BiomeControllerTest {

    @InjectMocks
    private BiomeController biomeController;

    @Mock
    private BiomeService biomeService;

    private BiomeOutPutDTO expectedDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        expectedDto = BiomeOutPutDTO.builder()
                .id(1L)
                .name("Test")
                .resourceLocations(new ArrayList<>())
                .build();
    }

  @Test
  void createBiome_ShouldReturnCreatedBiome() {
      // Given
      Biome biome = Biome.builder()
          .name("Test Biome")
          .build();

      Biome createdBiome = Biome.builder()
          .id(1L)
          .name("Test Biome")
          .locations(new ArrayList<>())
          .build();

      when(biomeService.createBiome(any(Biome.class))).thenReturn(createdBiome);

      // When
      ResponseEntity<BiomeOutPutDTO> response = biomeController.createBiome(biome);

      // Then
      verify(biomeService, times(1)).createBiome(biome);
      assertEquals(HttpStatus.CREATED, response.getStatusCode());
      assertNotNull(response.getBody());
      assertEquals(createdBiome.getId(), response.getBody().getId());
      assertEquals(createdBiome.getName(), response.getBody().getName());
  }

   @Test
   void getBiomeById_ShouldReturnBiome() {
       // Given
       Long id = 1L;
       Biome biome = Biome.builder()
           .id(1L)
           .name("Test Biome")
           .locations(new ArrayList<>())
           .build();
       when(biomeService.findBiomeById(id)).thenReturn(biome);

       // When
       ResponseEntity<BiomeOutPutDTO> response = biomeController.getBiomeById(id);

       // Then
       verify(biomeService, times(1)).findBiomeById(id);
       assertEquals(HttpStatus.OK, response.getStatusCode());
       assertNotNull(response.getBody());
       assertEquals(biome.getId(), response.getBody().getId());
       assertEquals(biome.getName(), response.getBody().getName());
   }
    @Test
    void getAllBiomes_ShouldReturnListOfBiomes() {
        // Given
        List<Biome> biomes = Arrays.asList(
            Biome.builder()
                .id(1L)
                .name("Test Biome 1")
                .locations(new ArrayList<>())
                .build(),
            Biome.builder()
                .id(2L)
                .name("Test Biome 2")
                .locations(new ArrayList<>())
                .build()
        );
        when(biomeService.findAllBiomes()).thenReturn(biomes);

        // When
        ResponseEntity<List<BiomeOutPutDTO>> response = biomeController.getAllBiomes();

        // Then
        verify(biomeService, times(1)).findAllBiomes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals(biomes.get(0).getId(), response.getBody().get(0).getId());
        assertEquals(biomes.get(0).getName(), response.getBody().get(0).getName());
        assertEquals(biomes.get(1).getId(), response.getBody().get(1).getId());
        assertEquals(biomes.get(1).getName(), response.getBody().get(1).getName());
    }

    @Test
    void updateBiome_ShouldReturnUpdatedBiome() {
        // Given
        Long id = 1L;
        Biome biome = Biome.builder()
            .name("Test Biome")
            .locations(new ArrayList<>())
            .build();

        Biome updatedBiome = Biome.builder()
            .id(id)
            .name("Updated Test Biome")
            .locations(new ArrayList<>())
            .build();

        when(biomeService.updateBiome(eq(id), any(Biome.class))).thenReturn(updatedBiome);

        // When
        ResponseEntity<BiomeOutPutDTO> response = biomeController.updateBiome(id, biome);

        // Then
        verify(biomeService, times(1)).updateBiome(eq(id), any(Biome.class));
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updatedBiome.getId(), response.getBody().getId());
        assertEquals(updatedBiome.getName(), response.getBody().getName());
    }

    @Test
    void deleteBiome_ShouldReturnNoContent() {
        // Given
        Long id = 1L;
        doNothing().when(biomeService).deleteBiome(id);

        // When
        ResponseEntity<Void> response = biomeController.deleteBiome(id);

        // Then
        verify(biomeService, times(1)).deleteBiome(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}