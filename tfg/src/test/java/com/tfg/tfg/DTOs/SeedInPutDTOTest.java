package com.tfg.tfg.DTOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeedInPutDTOTest {

    @Test
    void getSeed_value() {
        // Given
        SeedInPutDTO dto = new SeedInPutDTO("inputValue", "inputName");
        // When
        String actual = dto.getSeed_value();
        // Then
        assertEquals("inputValue", actual);
    }

    @Test
    void getName() {
        // Given
        SeedInPutDTO dto = new SeedInPutDTO("inputValue", "inputName");
        // When
        String actual = dto.getName();
        // Then
        assertEquals("inputName", actual);
    }

    @Test
    void setSeed_value() {
        // Given
        SeedInPutDTO dto = new SeedInPutDTO("initialValue", "initialName");
        // When
        dto.setSeed_value("updatedValue");
        // Then
        assertEquals("updatedValue", dto.getSeed_value());
    }

    @Test
    void setName() {
        // Given
        SeedInPutDTO dto = new SeedInPutDTO("initialValue", "initialName");
        // When
        dto.setName("updatedName");
        // Then
        assertEquals("updatedName", dto.getName());
    }

    @Test
    void testEquals() {
        // Given
        SeedInPutDTO a = new SeedInPutDTO("v", "n");
        SeedInPutDTO b = new SeedInPutDTO("v", "n");
        // When / Then
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertTrue(a.equals(a));            // reflexive
        assertFalse(a.equals(null));        // null
        assertFalse(a.equals("otherType")); // different type
    }

    @Test
    void canEqual() {
        // Given
        SeedInPutDTO dto = new SeedInPutDTO("x", "y");
        // When / Then
        assertTrue(dto.canEqual(new SeedInPutDTO("x", "y")));
        assertFalse(dto.canEqual("someString"));
    }

    @Test
    void testHashCode() {
        // Given
        SeedInPutDTO x = new SeedInPutDTO("same", "sameName");
        SeedInPutDTO y = new SeedInPutDTO("same", "sameName");
        // When / Then
        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testToString() {
        // Given
        SeedInPutDTO dto = new SeedInPutDTO("val", "nm");
        // When
        String s = dto.toString();
        // Then
        assertTrue(s.contains("SeedInPutDTO"));
        assertTrue(s.contains("seed_value=val"));
        assertTrue(s.contains("name=nm"));
    }

    @Test
    void builder() {
        // Given / When
        SeedInPutDTO dto = SeedInPutDTO.builder()
                .seed_value("bv")
                .name("bn")
                .build();
        // Then
        assertAll("builder",
                () -> assertEquals("bv", dto.getSeed_value()),
                () -> assertEquals("bn", dto.getName())
        );
    }
}
