package com.fasterEnvios.application.mappers;

import com.fasterEnvios.application.dto.packages.PackageRequestDTO;
import com.fasterEnvios.domain.model.PackageModel;

public class PackageAppMapper {

    public static PackageModel toModel (PackageRequestDTO dto){
        return PackageModel.builder()
                .withDeclaredValue(dto.declaredValue())
                .withWeightKg(dto.weightKg())
                .withDimensions(dto.dimensions())
                .withDescription(dto.description())
                .build();
    }

}
