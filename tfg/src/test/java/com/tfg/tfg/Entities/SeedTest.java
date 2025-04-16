package com.tfg.tfg.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class SeedTest {
    @Mock
    private ResourceLocation resourceLocation;
    @Mock
    private User user;

    private Seed seed;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ArrayList<ResourceLocation> locations = new ArrayList<>();
        locations.add(resourceLocation);

        seed = Seed.builder()
                .id(1L)
                .seed_value("12345678")
                .resourceLocationList(locations)
                .user(user)
                .build();
    }

    @Test
    void testSeedProperties() {
        assertEquals(1L, seed.getId());
        assertEquals("12345678", seed.getSeed_value());
        assertEquals(1, seed.getResourceLocationList().size());
        assertEquals(user, seed.getUser());
    }
}