package com.tfg.tfg.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ResourcesTest {
    @Mock
    private ResourceLocation resourceLocation;
    @Mock
    private MaterialRecipe materialRecipe;

    private Resources resources;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ArrayList<ResourceLocation> locations = new ArrayList<>();
        locations.add(resourceLocation);
        ArrayList<MaterialRecipe> materials = new ArrayList<>();
        materials.add(materialRecipe);

        resources = Resources.builder()
                .id(1L)
                .name("Diamond")
                .locations(locations)
                .materialRecipe(materials)
                .build();
    }

    @Test
    void testResourcesProperties() {
        assertEquals(1L, resources.getId());
        assertEquals("Diamond", resources.getName());
        assertEquals(1, resources.getLocations().size());
        assertEquals(1, resources.getMaterialRecipe().size());
    }
}