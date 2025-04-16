package com.tfg.tfg.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "resource_location")
public class ResourceLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int coordinateX;

    private int coordinateY;

    private int coordinateZ;

    @ManyToOne
    @JoinColumn(name = "seed_id")
    private Seed seed;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resources resource;

    @ManyToOne
    @JoinColumn(name = "biome_id")
    private Biome biome;
}
