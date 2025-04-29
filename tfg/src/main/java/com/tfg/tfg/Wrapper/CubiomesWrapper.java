package com.tfg.tfg.Wrapper;

public class CubiomesWrapper {
    // Constantes para tipos de estructuras
    public static final int VILLAGE = 1;
    public static final int MANSION = 2;
    public static final int MONUMENT = 3;
    public static final int FORTRESS = 4;
    public static final int TEMPLE = 5;
    // ... etc

    static {
        try {
            String libraryPath = System.getProperty("user.dir");
            System.load(libraryPath + "/cubiomes_jni.dll");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }
    
    public native void initialize();
    public native void setupSeed(long seed, int mcVersion);
    public native int[] findStructure(int structureType, int startX, int startZ, int radius);
    public native int getBiomeAt(int x, int y, int z);
}
