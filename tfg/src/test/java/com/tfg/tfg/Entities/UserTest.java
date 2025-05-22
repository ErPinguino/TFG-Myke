package com.tfg.tfg.Entities;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getId() {
        // Given
        User user = new User();
        user.setId(123L);
        // When
        Long actual = user.getId();
        // Then
        assertEquals(123L, actual);
    }

    @Test
    void getName() {
        // Given
        User user = new User();
        user.setName("testUser");
        // When
        String actual = user.getName();
        // Then
        assertEquals("testUser", actual);
    }

    @Test
    void getPassword() {
        // Given
        User user = new User();
        user.setPassword("secret");
        // When
        String actual = user.getPassword();
        // Then
        assertEquals("secret", actual);
    }

    @Test
    void getSeeds() {
        // Given
        Seed s1 = new Seed(); s1.setId(1L);
        Seed s2 = new Seed(); s2.setId(2L);
        List<Seed> seeds = Arrays.asList(s1, s2);
        User user = new User();
        user.setSeeds(seeds);
        // When
        List<Seed> actual = user.getSeeds();
        // Then
        assertSame(seeds, actual);
        assertEquals(2, actual.size());
        assertTrue(actual.contains(s1) && actual.contains(s2));
    }

    @Test
    void setId() {
        // Given
        User user = new User();
        // When
        user.setId(555L);
        // Then
        assertEquals(555L, user.getId());
    }

    @Test
    void setName() {
        // Given
        User user = new User();
        // When
        user.setName("newName");
        // Then
        assertEquals("newName", user.getName());
    }

    @Test
    void setPassword() {
        // Given
        User user = new User();
        // When
        user.setPassword("newPass");
        // Then
        assertEquals("newPass", user.getPassword());
    }

    @Test
    void setSeeds() {
        // Given
        User user = new User();
        List<Seed> seeds = Arrays.asList(new Seed());
        // When
        user.setSeeds(seeds);
        // Then
        assertSame(seeds, user.getSeeds());
    }

    @Test
    void testEquals() {
        // Given
        Seed s1 = new Seed(); s1.setId(1L);
        Seed s2 = new Seed(); s2.setId(2L);
        List<Seed> seeds = Arrays.asList(s1, s2);
        User a = User.builder()
                .id(10L)
                .name("u")
                .password("p")
                .seeds(seeds)
                .build();
        User b = User.builder()
                .id(10L)
                .name("u")
                .password("p")
                .seeds(seeds)
                .build();
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
        User user = new User();
        // When / Then
        assertTrue(user.canEqual(new User()));
        assertFalse(user.canEqual("notAUser"));
    }

    @Test
    void testHashCode() {
        // Given
        User x = User.builder()
                .id(20L)
                .name("n")
                .password("p")
                .seeds(Arrays.asList())
                .build();
        User y = User.builder()
                .id(20L)
                .name("n")
                .password("p")
                .seeds(Arrays.asList())
                .build();
        // When / Then
        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    void testToString() {
        // Given
        User user = User.builder()
                .id(30L)
                .name("nm")
                .password("pw")
                .seeds(Arrays.asList())
                .build();
        // When
        String s = user.toString();
        // Then
        assertTrue(s.contains("User("));
        assertTrue(s.contains("id=30"));
        assertTrue(s.contains("name=nm"));
        assertTrue(s.contains("password=pw"));
    }

    @Test
    void builder() {
        // Given / When
        Seed s = new Seed(); s.setId(7L);
        List<Seed> seeds = Arrays.asList(s);
        User user = User.builder()
                .id(77L)
                .name("builderName")
                .password("builderPass")
                .seeds(seeds)
                .build();
        // Then
        assertAll("builder",
                () -> assertEquals(77L, user.getId()),
                () -> assertEquals("builderName", user.getName()),
                () -> assertEquals("builderPass", user.getPassword()),
                () -> assertSame(seeds, user.getSeeds())
        );
    }
}
