package com.tfg.tfg.DTOs;

import com.tfg.tfg.Entities.Seed;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SeedOutPutDTO {

    private Long id;

    private String seed_value;



}
