package com.fasterEnvios.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageMovementEntity {

    private Long id;
    private PackageEntity packageEntity;
    private OfficeEntity officeEntity;
    private EmployeeEntity employeeEntity;
    private LocalDateTime movementDateTime;
    private String movementType;

}
