package com.tfg.tfg.services;

import com.tfg.tfg.entities.Seed;
import com.tfg.tfg.entities.User;
import com.tfg.tfg.repository.SeedRepository;
import com.tfg.tfg.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SeedServiceTest {

    @Mock
    private SeedRepository seeds;

    @Mock
    private UserRepository users;

    @InjectMocks
    private SeedService service;

    @Test
    void findByUser() {
        // Given
        Long userId = 10L;
        Seed s1 = new Seed(); s1.setId(1L);
        Seed s2 = new Seed(); s2.setId(2L);
        given(seeds.findByUserId(userId)).willReturn(Arrays.asList(s1, s2));

        // When
        List<Seed> result = service.findByUser(userId);

        // Then
        then(seeds).should().findByUserId(userId);
        assertEquals(2, result.size());
        assertTrue(result.contains(s1) && result.contains(s2));
    }

    @Test
    void save() {
        // Given
        Long userId = 20L;
        String value = "seedValue";
        String name = "seedName";
        User user = new User(); user.setId(userId);
        given(users.findById(userId)).willReturn(Optional.of(user));
        Seed expected = new Seed();
        given(seeds.save(any(Seed.class))).willReturn(expected);

        // When
        Seed actual = service.save(userId, value, name);

        // Then
        then(users).should().findById(userId);
        then(seeds).should().save(any(Seed.class));
        assertSame(expected, actual);
    }

    @Test
    void delete() {
        // Given
        Long userId = 30L;
        Long seedId = 40L;
        User user = new User(); user.setId(userId);
        Seed seed = new Seed(); seed.setId(seedId); seed.setUser(user);
        given(seeds.findById(seedId)).willReturn(Optional.of(seed));

        // When
        service.delete(userId, seedId);

        // Then
        then(seeds).should().delete(seed);
    }
}
