package com.fasterEnvios.infrastructure.mapper;

import com.fasterEnvios.domain.model.PackageModel;
import com.fasterEnvios.infrastructure.entity.PackageEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PackageInfraMapper {

    public PackageEntity toEntity(PackageModel model) {

        return PackageEntity.builder()
                .declaredValue(model.getDeclaredValue())
                .description(model.getDescription())
                .dimensions(model.getDimensions())
                .weightKg(model.getWeightKg())
                .build();

    }

    public List<PackageEntity> toEntityList(List<PackageModel> models) {
        if (models == null) return List.of();

        return models.stream()
                .map(this::toEntity)
                .toList();
    }


    public List<PackageModel> toModelList(List<PackageEntity> entities) {
        return entities.stream()
                .map(entity -> PackageModel.builder()
                        .withId(entity.getId())
                        .withDeclaredValue(entity.getDeclaredValue())
                        .withDescription(entity.getDescription())
                        .withDimensions(entity.getDimensions())
                        .withWeightKg(entity.getWeightKg())
                        .build())
                .toList();
    }

}
