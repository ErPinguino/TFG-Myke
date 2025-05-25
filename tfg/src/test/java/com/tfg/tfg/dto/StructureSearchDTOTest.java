package com.tfg.tfg.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StructureSearchDTOTest {

    @Test
    void getSeed() {
        // Given
        StructureSearchDTO dto = new StructureSearchDTO();
        dto.setSeed(123L);
        // When
        long actual = dto.getSeed();
        // Then
        assertEquals(123L, actual);
    }

    @Test
    void setSeed() {
        // Given
        StructureSearchDTO dto = new StructureSearchDTO();
        // When
        dto.setSeed(456L);
        // Then
        assertEquals(456L, dto.getSeed());
    }

    @Test
    void getStructureType() {
        // Given
        StructureSearchDTO dto = new StructureSearchDTO();
        dto.setStructureType(7);
        // When
        int actual = dto.getStructureType();
        // Then
        assertEquals(7, actual);
    }

    @Test
    void setStructureType() {
        // Given
        StructureSearchDTO dto = new StructureSearchDTO();
        // When
        dto.setStructureType(9);
        // Then
        assertEquals(9, dto.getStructureType());
    }

    @Test
    void getStartX() {
        // Given
        StructureSearchDTO dto = new StructureSearchDTO();
        dto.setStartX(10);
        // When
        int actual = dto.getStartX();
        // Then
        assertEquals(10, actual);
    }

    @Test
    void setStartX() {
        // Given
        StructureSearchDTO dto = new StructureSearchDTO();
        // When
        dto.setStartX(20);
        // Then
        assertEquals(20, dto.getStartX());
    }

    @Test
    void getStartZ() {
        // Given
        StructureSearchDTO dto = new StructureSearchDTO();
        dto.setStartZ(-5);
        // When
        int actual = dto.getStartZ();
        // Then
        assertEquals(-5, actual);
    }

    @Test
    void setStartZ() {
        // Given
        StructureSearchDTO dto = new StructureSearchDTO();
        // When
        dto.setStartZ(15);
        // Then
        assertEquals(15, dto.getStartZ());
    }

    @Test
    void getRadius() {
        // Given
        StructureSearchDTO dto = new StructureSearchDTO();
        dto.setRadius(100);
        // When
        int actual = dto.getRadius();
        // Then
        assertEquals(100, actual);
    }

    @Test
    void setRadius() {
        // Given
        StructureSearchDTO dto = new StructureSearchDTO();
        // When
        dto.setRadius(250);
        // Then
        assertEquals(250, dto.getRadius());
    }
}
