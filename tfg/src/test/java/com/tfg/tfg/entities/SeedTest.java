package com.tfg.tfg.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SeedTest {

    @Test
    void getId() {
        Seed seed = new Seed();
        seed.setId(42L);
        assertEquals(42L, seed.getId());
    }

    @Test
    void getSeed_value() {
        Seed seed = new Seed();
        seed.setSeedValue("myValue");
        assertEquals("myValue", seed.getSeedValue());
    }

    @Test
    void getName() {
        Seed seed = new Seed();
        seed.setName("myName");
        assertEquals("myName", seed.getName());
    }

    @Test
    void getCreated_at() {
        LocalDateTime now = LocalDateTime.of(2025, 5, 22, 14, 0);
        Seed seed = new Seed();
        seed.setCreatedAt(now);
        assertEquals(now, seed.getCreatedAt());
    }

    @Test
    void getUser() {
        User user = new User();
        user.setId(7L);
        Seed seed = new Seed();
        seed.setUser(user);
        assertSame(user, seed.getUser());
    }

    @Test
    void setId() {
        Seed seed = new Seed();
        seed.setId(100L);
        assertEquals(100L, seed.getId());
    }

    @Test
    void setSeed_value() {
        Seed seed = new Seed();
        seed.setSeedValue("anotherValue");
        assertEquals("anotherValue", seed.getSeedValue());
    }

    @Test
    void setName() {
        Seed seed = new Seed();
        seed.setName("anotherName");
        assertEquals("anotherName", seed.getName());
    }

    @Test
    void setCreated_at() {
        LocalDateTime ts = LocalDateTime.of(2021, 1, 1, 0, 0);
        Seed seed = new Seed();
        seed.setCreatedAt(ts);
        assertEquals(ts, seed.getCreatedAt());
    }

    @Test
    void setUser() {
        User u = new User();
        u.setId(99L);
        Seed seed = new Seed();
        seed.setUser(u);
        assertSame(u, seed.getUser());
    }

    @Test
    void testEquals() {
        LocalDateTime date = LocalDateTime.of(2025, 5, 22, 9, 30);
        User user = new User(); user.setId(1L);

        Seed a = Seed.builder()
                .id(1L)
                .seedValue("v")
                .name("n")
                .createdAt(date)
                .user(user)
                .build();
        Seed b = Seed.builder()
                .id(1L)
                .seedValue("v")
                .name("n")
                .createdAt(date)
                .user(user)
                .build();

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertTrue(a.equals(a));
        assertFalse(a.equals(null));
        assertFalse(a.equals("notASeed"));
    }

    @Test
    void canEqual() {
        Seed seed = new Seed();
        assertTrue(seed.canEqual(new Seed()));
        assertFalse(seed.canEqual("someString"));
    }

    @Test
    void testHashCode() {
        LocalDateTime date = LocalDateTime.of(2025, 5, 22, 9, 30);
        User user = new User(); user.setId(2L);

        Seed x = Seed.builder()
                .id(2L)
                .seedValue("x")
                .name("y")
                .createdAt(date)
                .user(user)
                .build();
        Seed y = Seed.builder()
                .id(2L)
                .seedValue("x")
                .name("y")
                .createdAt(date)
                .user(user)
                .build();

        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testToString() {
        LocalDateTime date = LocalDateTime.of(2025, 5, 22, 16, 45);
        User user = new User(); user.setId(3L);

        Seed seed = Seed.builder()
                .id(3L)
                .seedValue("val")
                .name("nm")
                .createdAt(date)
                .user(user)
                .build();
        String s = seed.toString();
        assertTrue(s.contains("Seed("));
        assertTrue(s.contains("id=3"));
        assertTrue(s.contains("seed_value=val"));
        assertTrue(s.contains("name=nm"));
    }

    @Test
    void builder() {
        LocalDateTime date = LocalDateTime.of(2025, 5, 22, 11, 15);
        User user = new User(); user.setId(5L);

        Seed seed = Seed.builder()
                .id(5L)
                .seedValue("bv")
                .name("bn")
                .createdAt(date)
                .user(user)
                .build();

        assertAll("builder",
                () -> assertEquals(5L, seed.getId()),
                () -> assertEquals("bv", seed.getSeedValue()),
                () -> assertEquals("bn", seed.getName()),
                () -> assertEquals(date, seed.getCreatedAt()),
                () -> assertSame(user, seed.getUser())
        );
    }
}
