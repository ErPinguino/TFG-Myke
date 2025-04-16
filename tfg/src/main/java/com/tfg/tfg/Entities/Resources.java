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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "resource")
    private List<ResourceLocation> locations = new ArrayList<>();

    @OneToMany(mappedBy = "resource")
    private List<MaterialRecipe> materialRecipe = new ArrayList<>();
}
