package com.tfg.tfg.Wrapper;

public class CubiomesTest {
    // Mapa de IDs de biomas a nombres
    private static String getBiomeName(int biomeId) {
        switch (biomeId) {
            case 1: return "Plains";
            case 2: return "Desert";
            case 3: return "Mountains";
            case 4: return "Forest";
            case 5: return "Taiga";
            case 25: return "Snowy Plains";
            // ... añadir más biomas según necesites
            default: return "Unknown Biome (" + biomeId + ")";
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Library path: " + System.getProperty("user.dir"));
            
            CubiomesWrapper wrapper = new CubiomesWrapper();
            wrapper.initialize();
            wrapper.setupSeed(12345L, 19); // Para MC 1.19
            
            // Buscar aldea más cercana
            int[] villageCoords = wrapper.findStructure(CubiomesWrapper.VILLAGE, 0, 0, 1000);
            System.out.printf("Aldea más cercana en: X=%d, Z=%d%n", 
                villageCoords[0], villageCoords[1]);
            
            // Obtener bioma en esas coordenadas
            int biomeId = wrapper.getBiomeAt(villageCoords[0], 64, villageCoords[1]);
            System.out.printf("Bioma en la aldea: %s (ID: %d)%n", 
                getBiomeName(biomeId), biomeId);
            
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Error loading native library: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
