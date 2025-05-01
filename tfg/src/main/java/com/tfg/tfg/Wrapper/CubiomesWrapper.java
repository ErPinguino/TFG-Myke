package com.tfg.tfg.Wrapper;

public class CubiomesWrapper {
    // Constantes para tipos de estructuras
    public static final int VILLAGE = 1;
    public static final int MANSION = 2; 
    public static final int MONUMENT = 3;
    public static final int FORTRESS = 4;
    public static final int TEMPLE = 5;
    public static final int STRONGHOLD = 6;
    public static final int MINESHAFT = 7;
    public static final int END_CITY = 8;
    public static final int NETHER_FORTRESS = 9;
    public static final int END_PORTAL = 10;
    public static final int OUTPOST = 11;
    public static final int BASTION = 12;

    // Constantes para biomas
    public static final int PLAINS = 1;
    public static final int DESERT = 2;
    public static final int MOUNTAINS = 3;
    public static final int FOREST = 4;
    public static final int TAIGA = 5;
    public static final int SWAMP = 6;
    public static final int RIVER = 7;
    public static final int NETHER_WASTES = 8;
    public static final int THE_END = 9;
    public static final int FROZEN_OCEAN = 10;
    public static final int FROZEN_RIVER = 11;
    public static final int SNOWY_PLAINS = 12;
    public static final int MUSHROOM_FIELDS = 14;
    public static final int DARK_FOREST = 29;
    public static final int OCEAN = 0;

    static {
        try {
            String libraryPath = System.getProperty("user.dir");
            System.load(libraryPath + "/cubiomes_jni.dll");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

    // Métodos nativos
    public native void initialize();
    public native void setupSeed(long seed, int mcVersion);
    public native int[] findStructure(int structureType, int startX, int startZ, int radius);

    /**
     * Obtiene el ID del bioma en las coordenadas especificadas
     * @param x coordenada X
     * @param y coordenada Y
     * @param z coordenada Z
     * @return ID del bioma en esa posición
     */
    public native int getBiomeAt(int x, int y, int z);
}
