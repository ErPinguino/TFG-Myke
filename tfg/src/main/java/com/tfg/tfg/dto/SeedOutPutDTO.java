package com.tfg.tfg.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeedOutPutDTO {

    private Long id;

    private String seedValue;

    private String name;



}
