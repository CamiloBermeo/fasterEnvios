package com.fasterEnvios.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageEntity {
    private Long id;
    private double weightKg;
    private double dimensions;
    private BigDecimal declaredValue;
    private String description;
}
