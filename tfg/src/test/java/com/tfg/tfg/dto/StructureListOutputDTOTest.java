package com.tfg.tfg.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StructureListOutputDTOTest {

    @Test
    void isFound() {
        // Given
        StructureListOutputDTO dto = new StructureListOutputDTO();
        // When
        dto.setFound(true);
        // Then
        assertTrue(dto.isFound());
    }

    @Test
    void getMessage() {
        // Given
        StructureListOutputDTO dto = new StructureListOutputDTO();
        dto.setMessage("Test message");
        // When
        String actual = dto.getMessage();
        // Then
        assertEquals("Test message", actual);
    }

    @Test
    void getCoordinates() {
        // Given
        StructureListOutputDTO dto = new StructureListOutputDTO();
        List<StructureListOutputDTO.Coordinate> coords = List.of(
                new StructureListOutputDTO.Coordinate(1, 2),
                new StructureListOutputDTO.Coordinate(3, 4)
        );
        dto.setCoordinates(coords);
        // When
        List<StructureListOutputDTO.Coordinate> actual = dto.getCoordinates();
        // Then
        assertSame(coords, actual);
        assertEquals(2, actual.size());
        assertEquals(1, actual.get(0).getX());
        assertEquals(4, actual.get(1).getZ());
    }

    @Test
    void setFound() {
        // Given
        StructureListOutputDTO dto = new StructureListOutputDTO();
        // When
        dto.setFound(false);
        // Then
        assertFalse(dto.isFound());
    }

    @Test
    void setMessage() {
        // Given
        StructureListOutputDTO dto = new StructureListOutputDTO();
        // When
        dto.setMessage("Another message");
        // Then
        assertEquals("Another message", dto.getMessage());
    }

    @Test
    void setCoordinates() {
        // Given
        StructureListOutputDTO dto = new StructureListOutputDTO();
        List<StructureListOutputDTO.Coordinate> coords = List.of(new StructureListOutputDTO.Coordinate(5, 6));
        // When
        dto.setCoordinates(coords);
        // Then
        assertSame(coords, dto.getCoordinates());
    }

    @Test
    void testEquals() {
        // Given
        List<StructureListOutputDTO.Coordinate> coords = List.of(
                new StructureListOutputDTO.Coordinate(7, 8)
        );
        StructureListOutputDTO a = StructureListOutputDTO.builder()
                .found(true)
                .message("OK")
                .coordinates(coords)
                .build();
        StructureListOutputDTO b = StructureListOutputDTO.builder()
                .found(true)
                .message("OK")
                .coordinates(coords)
                .build();
        // Then
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertTrue(a.equals(a));            // reflexive
        assertFalse(a.equals(null));        // null
        assertFalse(a.equals("otherType")); // different type
    }

    @Test
    void canEqual() {
        // Given
        StructureListOutputDTO dto = new StructureListOutputDTO();
        // Then
        assertTrue(dto.canEqual(new StructureListOutputDTO()));
        assertFalse(dto.canEqual("notADTO"));
    }

    @Test
    void testHashCode() {
        // Given
        List<StructureListOutputDTO.Coordinate> coords = List.of();
        StructureListOutputDTO x = StructureListOutputDTO.builder()
                .found(false)
                .message("X")
                .coordinates(coords)
                .build();
        StructureListOutputDTO y = StructureListOutputDTO.builder()
                .found(false)
                .message("X")
                .coordinates(coords)
                .build();
        // Then
        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testToString() {
        // Given
        List<StructureListOutputDTO.Coordinate> coords = List.of(new StructureListOutputDTO.Coordinate(9, 10));
        StructureListOutputDTO dto = StructureListOutputDTO.builder()
                .found(true)
                .message("Found")
                .coordinates(coords)
                .build();
        // When
        String s = dto.toString();
        // Then
        assertTrue(s.contains("found=true"));
        assertTrue(s.contains("message=Found"));
        assertTrue(s.contains("coordinates"));
    }

    @Test
    void builder() {
        // Given / When
        List<StructureListOutputDTO.Coordinate> coords = List.of(
                new StructureListOutputDTO.Coordinate(11, 12)
        );
        StructureListOutputDTO dto = StructureListOutputDTO.builder()
                .found(true)
                .message("Built")
                .coordinates(coords)
                .build();
        // Then
        assertAll("builder",
                () -> assertTrue(dto.isFound()),
                () -> assertEquals("Built", dto.getMessage()),
                () -> assertSame(coords, dto.getCoordinates())
        );
    }
}
