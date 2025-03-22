package com.tfg.tfg.Characters;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Characters {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private String image;

    private String type;

    private String rarity;

    private String element;
    
    private String weapon;
}
