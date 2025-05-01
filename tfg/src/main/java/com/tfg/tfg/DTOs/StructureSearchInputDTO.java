package com.tfg.tfg.DTOs;

import lombok.Data;


@Data
public class StructureSearchInputDTO {
    private String structureName;
    
    private Long seed;
    
    private int x = 0;
    private int z = 0;
    private int radius = 2000;
}