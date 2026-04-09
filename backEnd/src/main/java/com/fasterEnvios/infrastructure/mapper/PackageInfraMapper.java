package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.domain.model.PackageModel;
import com.fasterEnvios.infrastructure.entity.PackageEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PackageInfraMapper {

    public static PackageEntity toEntity(PackageModel model) {

        return PackageEntity.builder()
                .declaredValue(model.getDeclaredValue())
                .description(model.getDescription())
                .dimensions(model.getDimensions())
                .weightKg(model.getWeightKg())
                .build();

    }


    public static PackageModel toModel(PackageEntity entity) {
        return PackageModel.builder()
                .withId(entity.getId())
                .withDeclaredValue(entity.getDeclaredValue())
                .withDescription(entity.getDescription())
                .withDimensions(entity.getDimensions())
                .withWeightKg(entity.getWeightKg())
                .build();
    }

}
