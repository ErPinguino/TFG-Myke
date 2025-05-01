package com.tfg.tfg.DTOs;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class StructureSearchOutputDTO {
    private boolean success;
    private String message;
    private Coordinates coordinates;

    @Data
    @AllArgsConstructor
    public static class Coordinates {
        private int x;
        private int z;
    }
}