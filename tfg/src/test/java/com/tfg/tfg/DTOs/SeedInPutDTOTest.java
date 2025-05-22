package com.tfg.tfg.DTOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeedInPutDTOTest {

    @Test
    void getSeed_value() {
        SeedInPutDTO dto = new SeedInPutDTO();
        dto.setSeed_value("inputValue");
        assertEquals("inputValue", dto.getSeed_value());
    }

    @Test
    void getName() {
        SeedInPutDTO dto = new SeedInPutDTO();
        dto.setName("inputName");
        assertEquals("inputName", dto.getName());
    }

    @Test
    void setSeed_value() {
        SeedInPutDTO dto = new SeedInPutDTO();
        dto.setSeed_value("anotherValue");
        assertEquals("anotherValue", dto.getSeed_value());
    }

    @Test
    void setName() {
        SeedInPutDTO dto = new SeedInPutDTO();
        dto.setName("anotherName");
        assertEquals("anotherName", dto.getName());
    }

    @Test
    void testEquals() {
        SeedInPutDTO a = SeedInPutDTO.builder()
                .seed_value("v")
                .name("n")
                .build();
        SeedInPutDTO b = SeedInPutDTO.builder()
                .seed_value("v")
                .name("n")
                .build();

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        // reflexive, null and different type
        assertTrue(a.equals(a));
        assertFalse(a.equals(null));
        assertFalse(a.equals("notADTO"));
    }

    @Test
    void canEqual() {
        SeedInPutDTO dto = SeedInPutDTO.builder()
                .seed_value("x")
                .name("y")
                .build();
        assertTrue(dto.canEqual(SeedInPutDTO.builder().seed_value("x").name("y").build()));
        assertFalse(dto.canEqual("someString"));
    }

    @Test
    void testHashCode() {
        SeedInPutDTO x = SeedInPutDTO.builder()
                .seed_value("same")
                .name("sameName")
                .build();
        SeedInPutDTO y = SeedInPutDTO.builder()
                .seed_value("same")
                .name("sameName")
                .build();

        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testToString() {
        SeedInPutDTO dto = SeedInPutDTO.builder()
                .seed_value("val")
                .name("nm")
                .build();
        String s = dto.toString();
        assertTrue(s.contains("SeedInPutDTO"));
        assertTrue(s.contains("seed_value=val"));
        assertTrue(s.contains("name=nm"));
    }

    @Test
    void builder() {
        SeedInPutDTO dto = SeedInPutDTO.builder()
                .seed_value("bv")
                .name("bn")
                .build();

        assertAll("builder",
                () -> assertEquals("bv", dto.getSeed_value()),
                () -> assertEquals("bn", dto.getName())
        );
    }
}
