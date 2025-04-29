package com.tfg.tfg.Services;

import com.tfg.tfg.DTOs.StructureSearchDTO;
import com.tfg.tfg.Wrapper.CubiomesWrapper;
import org.springframework.stereotype.Service;

@Service
public class StructureFinderService {
    private final CubiomesWrapper wrapper;

    public StructureFinderService() {
        this.wrapper = new CubiomesWrapper();
        this.wrapper.initialize();
    }

    public int[] findStructure(StructureSearchDTO search) {
        wrapper.setupSeed(search.getSeed(), 19); // MC 1.19
        return wrapper.findStructure(
            search.getStructureType(),
            search.getStartX(),
            search.getStartZ(),
            search.getRadius()
        );
    }

    public String getBiomeAt(int x, int y, int z) {
        int biomeId = wrapper.getBiomeAt(x, y, z);
        return getBiomeName(biomeId);
    }

    private String getBiomeName(int biomeId) {
        // ... mismo código que en CubiomesTest
        switch (biomeId) {
            case 1: return "Plains";
            case 2: return "Desert";
            // ... etc
            default: return "Unknown Biome (" + biomeId + ")";
        }
    }
}