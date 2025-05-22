package com.tfg.tfg.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SeedInPutDTO {

    private String seed_value;

    private String name;

}
