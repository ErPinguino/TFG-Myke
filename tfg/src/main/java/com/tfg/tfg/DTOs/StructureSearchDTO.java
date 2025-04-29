package com.tfg.tfg.DTOs;

public class StructureSearchDTO {
    private long seed;
    private int structureType;
    private int startX;
    private int startZ;
    private int radius;

    // Getters y setters
    public long getSeed() { return seed; }
    public void setSeed(long seed) { this.seed = seed; }
    public int getStructureType() { return structureType; }
    public void setStructureType(int structureType) { this.structureType = structureType; }
    public int getStartX() { return startX; }
    public void setStartX(int startX) { this.startX = startX; }
    public int getStartZ() { return startZ; }
    public void setStartZ(int startZ) { this.startZ = startZ; }
    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; }
}