package com.tfg.tfg.Wrapper;

public class CubiomesTest {
    public static void main(String[] args) {
        try {
            CubiomesWrapper wrapper = new CubiomesWrapper();
            wrapper.initialize();
            wrapper.setupSeed(12345L, 19); // MC 1.19
            
            // Definir las estructuras a buscar
            int[] structureTypes = {
                CubiomesWrapper.MANSION,
                CubiomesWrapper.STRONGHOLD,
                CubiomesWrapper.END_PORTAL,
                CubiomesWrapper.MONUMENT,
                CubiomesWrapper.VILLAGE,
                CubiomesWrapper.FORTRESS,
                CubiomesWrapper.END_CITY,
                CubiomesWrapper.BASTION
            };
            
            String[] structureNames = {
                "Mansión",
                "Stronghold",
                "Portal del End",
                "Monumento",
                "Aldea",
                "Fortaleza del Nether",
                "Ciudad del End",
                "Bastión"
            };
            
            // Radio de búsqueda en bloques
            int searchRadius = 2000;
            
            System.out.println("Buscando estructuras en un radio de " + searchRadius + " bloques...\n");
            
            for(int i = 0; i < structureTypes.length; i++) {
                int[] coords = wrapper.findStructure(structureTypes[i], 0, 0, searchRadius);
                
                if(coords != null) {
                    System.out.printf("%s encontrada en: X=%d, Z=%d%n", 
                        structureNames[i], coords[0], coords[1]);
                } else {
                    System.out.printf("%s no encontrada en el radio de %d bloques%n", 
                        structureNames[i], searchRadius);
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
