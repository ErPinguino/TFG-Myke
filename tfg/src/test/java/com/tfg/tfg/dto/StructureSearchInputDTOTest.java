package com.tfg.tfg.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StructureSearchInputDTOTest {

    @Test
    void getSeed() {
        // Given
        StructureSearchInputDTO dto = StructureSearchInputDTO.builder()
                .seed("1234567890123456789")
                .x(1.1)
                .z(2.2)
                .radius(100)
                .count(5)
                .structureName("Village")
                .build();
        // When
        String actual = dto.getSeed();
        // Then
        assertEquals("1234567890123456789", actual);
    }

    @Test
    void getX() {
        // Given
        StructureSearchInputDTO dto = StructureSearchInputDTO.builder()
                .seed("s")
                .x(42.5)
                .z(0.0)
                .radius(0)
                .count(0)
                .structureName("x")
                .build();
        // When
        Double actual = dto.getX();
        // Then
        assertEquals(42.5, actual);
    }

    @Test
    void getZ() {
        // Given
        StructureSearchInputDTO dto = StructureSearchInputDTO.builder()
                .seed("s")
                .x(0.0)
                .z(-7.7)
                .radius(0)
                .count(0)
                .structureName("z")
                .build();
        // When
        Double actual = dto.getZ();
        // Then
        assertEquals(-7.7, actual);
    }

    @Test
    void getRadius() {
        // Given
        StructureSearchInputDTO dto = new StructureSearchInputDTO();
        dto.setRadius(250);
        // When
        Integer actual = dto.getRadius();
        // Then
        assertEquals(250, actual);
    }

    @Test
    void getCount() {
        // Given
        StructureSearchInputDTO dto = new StructureSearchInputDTO();
        dto.setCount(10);
        // When
        Integer actual = dto.getCount();
        // Then
        assertEquals(10, actual);
    }

    @Test
    void getStructureName() {
        // Given
        StructureSearchInputDTO dto = new StructureSearchInputDTO();
        dto.setStructureName("Fortress");
        // When
        String actual = dto.getStructureName();
        // Then
        assertEquals("Fortress", actual);
    }

    @Test
    void setSeed() {
        // Given
        StructureSearchInputDTO dto = new StructureSearchInputDTO();
        // When
        dto.setSeed("seedValue");
        // Then
        assertEquals("seedValue", dto.getSeed());
    }

    @Test
    void setX() {
        // Given
        StructureSearchInputDTO dto = new StructureSearchInputDTO();
        // When
        dto.setX(15.15);
        // Then
        assertEquals(15.15, dto.getX());
    }

    @Test
    void setZ() {
        // Given
        StructureSearchInputDTO dto = new StructureSearchInputDTO();
        // When
        dto.setZ(-3.3);
        // Then
        assertEquals(-3.3, dto.getZ());
    }

    @Test
    void setRadius() {
        // Given
        StructureSearchInputDTO dto = new StructureSearchInputDTO();
        // When
        dto.setRadius(333);
        // Then
        assertEquals(333, dto.getRadius());
    }

    @Test
    void setCount() {
        // Given
        StructureSearchInputDTO dto = new StructureSearchInputDTO();
        // When
        dto.setCount(7);
        // Then
        assertEquals(7, dto.getCount());
    }

    @Test
    void setStructureName() {
        // Given
        StructureSearchInputDTO dto = new StructureSearchInputDTO();
        // When
        dto.setStructureName("Castle");
        // Then
        assertEquals("Castle", dto.getStructureName());
    }

    @Test
    void testEquals() {
        // Given
        StructureSearchInputDTO a = StructureSearchInputDTO.builder()
                .seed("abc")
                .x(1.0)
                .z(2.0)
                .radius(3)
                .count(4)
                .structureName("N")
                .build();
        StructureSearchInputDTO b = StructureSearchInputDTO.builder()
                .seed("abc")
                .x(1.0)
                .z(2.0)
                .radius(3)
                .count(4)
                .structureName("N")
                .build();
        // Then
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertTrue(a.equals(a));             // reflexive
        assertFalse(a.equals(null));         // null
        assertFalse(a.equals("otherType"));  // different type
    }

    @Test
    void canEqual() {
        // Given
        StructureSearchInputDTO dto = StructureSearchInputDTO.builder()
                .seed("x")
                .x(0.0)
                .z(0.0)
                .radius(0)
                .count(0)
                .structureName("y")
                .build();
        // Then
        assertTrue(dto.canEqual(new StructureSearchInputDTO()));
        assertFalse(dto.canEqual("notADTO"));
    }

    @Test
    void testHashCode() {
        // Given
        StructureSearchInputDTO x = StructureSearchInputDTO.builder()
                .seed("s")
                .x(1.1)
                .z(2.2)
                .radius(100)
                .count(5)
                .structureName("T")
                .build();
        StructureSearchInputDTO y = StructureSearchInputDTO.builder()
                .seed("s")
                .x(1.1)
                .z(2.2)
                .radius(100)
                .count(5)
                .structureName("T")
                .build();
        // Then
        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testToString() {
        // Given
        StructureSearchInputDTO dto = StructureSearchInputDTO.builder()
                .seed("seed123")
                .x(9.9)
                .z(8.8)
                .radius(77)
                .count(3)
                .structureName("P")
                .build();
        // When
        String s = dto.toString();
        // Then
        assertTrue(s.contains("StructureSearchInputDTO"));
        assertTrue(s.contains("seed=seed123"));
        assertTrue(s.contains("x=9.9"));
        assertTrue(s.contains("z=8.8"));
        assertTrue(s.contains("radius=77"));
        assertTrue(s.contains("count=3"));
        assertTrue(s.contains("structureName=P"));
    }

    @Test
    void builder() {
        // When
        StructureSearchInputDTO dto = StructureSearchInputDTO.builder()
                .seed("seedX")
                .x(4.4)
                .z(5.5)
                .radius(11)
                .count(22)
                .structureName("Fortune")
                .build();
        // Then
        assertAll("builder",
                () -> assertEquals("seedX", dto.getSeed()),
                () -> assertEquals(4.4, dto.getX()),
                () -> assertEquals(5.5, dto.getZ()),
                () -> assertEquals(11, dto.getRadius()),
                () -> assertEquals(22, dto.getCount()),
                () -> assertEquals("Fortune", dto.getStructureName())
        );
    }
}
