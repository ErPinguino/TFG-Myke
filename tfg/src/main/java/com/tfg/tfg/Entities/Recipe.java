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
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String object_name;

    private String imageUrl;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<MaterialRecipe> materials = new ArrayList<>();
}
