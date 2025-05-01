package com.tfg.tfg.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;  

    private int type;    
    
    private int coordinateX;  
    
    private int coordinateY; 
    
    private int coordinateZ; 
    
    
    @ManyToOne
    @JoinColumn(name = "seed_id")
    private Seed seed;         // Seed donde se encontró
    
    @ManyToOne
    @JoinColumn(name = "biome_id")
    private Biome biome;       // Bioma donde está la estructura
}