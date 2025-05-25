package com.tfg.tfg.services;

import com.tfg.tfg.dto.StructureSearchInputDTO;
import com.tfg.tfg.dto.StructureListOutputDTO;
import com.tfg.tfg.dto.StructureListOutputDTO.Coordinate;
import de.rasmusantons.cubiomes.Cubiomes;
import de.rasmusantons.cubiomes.Dimension;
import de.rasmusantons.cubiomes.Pos;
import de.rasmusantons.cubiomes.StructureType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StructureFinderServiceTest {

    @Spy
    private StructureFinderService service;

    @Mock
    private Cubiomes cubiomes;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(service, "cubiomes", cubiomes);
    }

    @Test
    void findStructures_invalidSeed() {
        // Given
        StructureSearchInputDTO input = StructureSearchInputDTO.builder()
                .seed("notANumber")
                .x(0.0).z(0.0)
                .radius(10).count(1)
                .structureName("Village")
                .build();
        // When
        StructureListOutputDTO dto = service.findStructures(input);
        // Then
        assertAll("invalidSeed",
                () -> assertFalse(dto.isFound()),
                () -> assertEquals("Invalid seed: notANumber", dto.getMessage()),
                () -> assertTrue(dto.getCoordinates().isEmpty())
        );
    }

    @Test
    void findStructures_invalidType() {
        // Given
        StructureSearchInputDTO input = StructureSearchInputDTO.builder()
                .seed("123")
                .x(0.0).z(0.0)
                .radius(10).count(1)
                .structureName("Nonexistent")
                .build();
        // stub seed parsing and applySeed
        // Will not throw when parsing, so applySeed is called
        willDoNothing().given(cubiomes).applySeed(eq(Dimension.DIM_OVERWORLD), eq(123L));
        // When
        StructureListOutputDTO dto = service.findStructures(input);
        // Then
        assertAll("invalidType",
                () -> assertFalse(dto.isFound()),
                () -> assertEquals("Structure type not available: Nonexistent", dto.getMessage()),
                () -> assertTrue(dto.getCoordinates().isEmpty())
        );
    }

    @Test
    void findStructures_noneFound() {
        // Given
        StructureSearchInputDTO input = StructureSearchInputDTO.builder()
                .seed("42")
                .x(0.0).z(0.0)
                .radius(16).count(1)
                .structureName("Village")
                .build();
        long seed = 42L;
        willDoNothing().given(cubiomes).applySeed(Dimension.DIM_OVERWORLD, seed);
        // stub all region probes to return a Pos but always mark as non-viable
        given(cubiomes.getStructurePos(eq(StructureType.Village), eq(seed), anyInt(), anyInt()))
                .willReturn(new Pos(0, 0));
        given(cubiomes.isViableStructurePos(eq(StructureType.Village), anyInt(), anyInt()))
                .willReturn(false);

        // When
        StructureListOutputDTO dto = service.findStructures(input);

        // Then
        then(cubiomes).should().applySeed(Dimension.DIM_OVERWORLD, seed);
        // we expect multiple probes, so verify at least one interaction
        then(cubiomes).should(atLeastOnce()).getStructurePos(
                eq(StructureType.Village), eq(seed), anyInt(), anyInt());
        then(cubiomes).should(atLeastOnce()).isViableStructurePos(
                eq(StructureType.Village), anyInt(), anyInt());
        assertAll("noneFound",
                () -> assertFalse(dto.isFound()),
                () -> assertEquals("No structures found", dto.getMessage()),
                () -> assertTrue(dto.getCoordinates().isEmpty())
        );
    }

    @Test
    void findStructures_foundOne() {
        // Given
        StructureSearchInputDTO input = StructureSearchInputDTO.builder()
                .seed("7")
                .x(0.0).z(0.0)
                .radius(16).count(1)
                .structureName("Village")
                .build();
        long seed = 7L;
        Pos pos = new Pos(100, 200);
        given(cubiomes.getStructurePos(StructureType.Village, seed, 0, 0))
                .willReturn(pos);
        given(cubiomes.isViableStructurePos(StructureType.Village, 100, 200))
                .willReturn(true);
        // When
        StructureListOutputDTO dto = service.findStructures(input);
        // Then
        then(cubiomes).should().applySeed(Dimension.DIM_OVERWORLD, seed);
        then(cubiomes).should().getStructurePos(StructureType.Village, seed, 0, 0);
        then(cubiomes).should().isViableStructurePos(StructureType.Village, 100, 200);
        List<Coordinate> coords = dto.getCoordinates();
        assertAll("foundOne",
                () -> assertTrue(dto.isFound()),
                () -> assertEquals("Found 1 Village", dto.getMessage()),
                () -> assertEquals(1, coords.size()),
                () -> assertEquals(100, coords.get(0).getX()),
                () -> assertEquals(200, coords.get(0).getZ())
        );
    }
}
