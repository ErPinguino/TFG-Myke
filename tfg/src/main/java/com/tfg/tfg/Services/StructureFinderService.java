package com.tfg.tfg.Services;

import com.tfg.tfg.DTOs.StructureSearchInputDTO;
import com.tfg.tfg.DTOs.StructureSearchOutputDTO;
import com.tfg.tfg.Wrapper.CubiomesWrapper;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

@Service
public class StructureFinderService {
    private final CubiomesWrapper wrapper;
    private final Map<String, Integer> structureTypes;
    private final Map<Integer, int[]> validBiomes;

    public StructureFinderService() {
        wrapper = new CubiomesWrapper();
        wrapper.initialize();
        
        // Mapeo de nombres de estructuras a sus IDs
        structureTypes = new HashMap<>();
        structureTypes.put("mansion", CubiomesWrapper.MANSION);
        structureTypes.put("stronghold", CubiomesWrapper.STRONGHOLD);
        structureTypes.put("endportal", CubiomesWrapper.END_PORTAL);
        structureTypes.put("monument", CubiomesWrapper.MONUMENT);
        structureTypes.put("village", CubiomesWrapper.VILLAGE);
        structureTypes.put("fortress", CubiomesWrapper.FORTRESS);
        structureTypes.put("endcity", CubiomesWrapper.END_CITY);
        structureTypes.put("bastion", CubiomesWrapper.BASTION);

        // Mapeo de estructuras a sus biomas válidos
        validBiomes = new HashMap<>();
        validBiomes.put(CubiomesWrapper.MANSION, new int[]{29}); // Dark Forest
        validBiomes.put(CubiomesWrapper.MONUMENT, new int[]{0}); // Ocean
        // ... añadir más mapeos de biomas
    }

    public StructureSearchOutputDTO findStructure(StructureSearchInputDTO input) {
        String structureName = input.getStructureName().toLowerCase().trim();
        Integer structureType = structureTypes.get(structureName);
        System.out.println("Received structure name: " + input.getStructureName());
        System.out.println("Converted to: " + structureName);
        System.out.println("Structure type resolved: " + structureType);
        
        if (structureType == null) {
            return new StructureSearchOutputDTO(false, 
                "Estructura no válida", null);
        }

        wrapper.setupSeed(input.getSeed(), 19); // MC 1.19
        int[] coords = wrapper.findStructure(structureType, 
            input.getX(), input.getZ(), input.getRadius());
        System.out.println("Coords found: " + (coords != null ? Arrays.toString(coords) : "null"));

        if (coords == null) {
            return new StructureSearchOutputDTO(false, 
                "Estructura no encontrada en el radio especificado", null);
        }

        // Verificar bioma si es necesario
        int[] validBiomesForStructure = validBiomes.get(structureType);
        if (validBiomesForStructure != null) {
            int biome = wrapper.getBiomeAt(coords[0], 64, coords[1]);
            boolean validBiome = false;
            for (int validBiomeId : validBiomesForStructure) {
                if (biome == validBiomeId) {
                    validBiome = true;
                    break;
                }
            }
            if (!validBiome) {
                return new StructureSearchOutputDTO(false, 
                    "Bioma no válido para esta estructura", null);
            }
        }

        return new StructureSearchOutputDTO(true, 
            "Estructura encontrada", 
            new StructureSearchOutputDTO.Coordinates(coords[0], coords[1]));
    }

    public Map<String, Integer> getAvailableStructures() {
        return new HashMap<>(structureTypes);
    }
}