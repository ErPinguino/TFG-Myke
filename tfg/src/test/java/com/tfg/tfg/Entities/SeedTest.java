package com.tfg.tfg.Entities;

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
        seed.setSeed_value("myValue");
        assertEquals("myValue", seed.getSeed_value());
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
        seed.setCreated_at(now);
        assertEquals(now, seed.getCreated_at());
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
        seed.setSeed_value("anotherValue");
        assertEquals("anotherValue", seed.getSeed_value());
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
        seed.setCreated_at(ts);
        assertEquals(ts, seed.getCreated_at());
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
                .seed_value("v")
                .name("n")
                .created_at(date)
                .user(user)
                .build();
        Seed b = Seed.builder()
                .id(1L)
                .seed_value("v")
                .name("n")
                .created_at(date)
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
                .seed_value("x")
                .name("y")
                .created_at(date)
                .user(user)
                .build();
        Seed y = Seed.builder()
                .id(2L)
                .seed_value("x")
                .name("y")
                .created_at(date)
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
                .seed_value("val")
                .name("nm")
                .created_at(date)
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
                .seed_value("bv")
                .name("bn")
                .created_at(date)
                .user(user)
                .build();

        assertAll("builder",
                () -> assertEquals(5L, seed.getId()),
                () -> assertEquals("bv", seed.getSeed_value()),
                () -> assertEquals("bn", seed.getName()),
                () -> assertEquals(date, seed.getCreated_at()),
                () -> assertSame(user, seed.getUser())
        );
    }
}
