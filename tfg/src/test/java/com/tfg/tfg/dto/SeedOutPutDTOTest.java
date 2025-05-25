package com.tfg.tfg.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeedOutPutDTOTest {

    @Test
    void getId() {
        // Given
        SeedOutPutDTO dto = new SeedOutPutDTO(10L, "val", "nm");
        // When
        Long actual = dto.getId();
        // Then
        assertEquals(10L, actual);
    }

    @Test
    void getSeed_value() {
        // Given
        SeedOutPutDTO dto = new SeedOutPutDTO(0L, "seedVal", "nameVal");
        // When
        String actual = dto.getSeedValue();
        // Then
        assertEquals("seedVal", actual);
    }

    @Test
    void getName() {
        // Given
        SeedOutPutDTO dto = new SeedOutPutDTO(0L, "seedVal", "nameVal");
        // When
        String actual = dto.getName();
        // Then
        assertEquals("nameVal", actual);
    }

    @Test
    void setId() {
        // Given
        SeedOutPutDTO dto = new SeedOutPutDTO(1L, "v", "n");
        // When
        dto.setId(2L);
        // Then
        assertEquals(2L, dto.getId());
    }

    @Test
    void setSeed_value() {
        // Given
        SeedOutPutDTO dto = new SeedOutPutDTO(1L, "oldVal", "n");
        // When
        dto.setSeedValue("newVal");
        // Then
        assertEquals("newVal", dto.getSeedValue());
    }

    @Test
    void setName() {
        // Given
        SeedOutPutDTO dto = new SeedOutPutDTO(1L, "v", "oldName");
        // When
        dto.setName("newName");
        // Then
        assertEquals("newName", dto.getName());
    }

    @Test
    void testEquals() {
        // Given
        SeedOutPutDTO a = new SeedOutPutDTO(5L, "v", "n");
        SeedOutPutDTO b = new SeedOutPutDTO(5L, "v", "n");
        // When / Then
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertTrue(a.equals(a));             // reflexive
        assertFalse(a.equals(null));         // null
        assertFalse(a.equals("otherType"));  // different type
    }

    @Test
    void canEqual() {
        // Given
        SeedOutPutDTO dto = new SeedOutPutDTO(1L, "x", "y");
        // When / Then
        assertTrue(dto.canEqual(new SeedOutPutDTO(1L, "x", "y")));
        assertFalse(dto.canEqual("someString"));
    }

    @Test
    void testHashCode() {
        // Given
        SeedOutPutDTO x = new SeedOutPutDTO(7L, "same", "sameName");
        SeedOutPutDTO y = new SeedOutPutDTO(7L, "same", "sameName");
        // When / Then
        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testToString() {
        // Given
        SeedOutPutDTO dto = new SeedOutPutDTO(3L, "val", "nm");
        // When
        String s = dto.toString();
        // Then
        assertTrue(s.contains("SeedOutPutDTO"));
        assertTrue(s.contains("id=3"));
        assertTrue(s.contains("seed_value=val"));
        assertTrue(s.contains("name=nm"));
    }

    @Test
    void builder() {
        // Given / When
        SeedOutPutDTO dto = SeedOutPutDTO.builder()
                .id(8L)
                .seedValue("bv")
                .name("bn")
                .build();
        // Then
        assertAll("builder",
                () -> assertEquals(8L, dto.getId()),
                () -> assertEquals("bv", dto.getSeedValue()),
                () -> assertEquals("bn", dto.getName())
        );
    }
}
